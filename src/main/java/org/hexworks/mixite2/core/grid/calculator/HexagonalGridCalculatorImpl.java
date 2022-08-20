package org.hexworks.mixite2.core.grid.calculator

import org.hexworks.mixite2.core.grid.HexagonalGrid
import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.geometry.Hexagon
import org.hexworks.mixite2.core.geometry.RotationDirection
import org.hexworks.mixite2.core.grid.GridCell
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round

class HexagonalGridCalculatorImpl(override val hexagonalGrid: HexagonalGrid) :
    HexagonalGridCalculator {

    override fun calculateDistanceBetween(first: GridCell, second: GridCell): Int {
        val absX = abs(first.getCoordinate().gridX - second.getCoordinate().gridX)
        val absY = abs(first.getCoordinate().gridY - second.getCoordinate().gridY)
        val absZ = abs(first.getCoordinate().gridZ - second.getCoordinate().gridZ)
        return max(max(absX, absY), absZ)
    }

    override fun calculateMovementRangeFrom(from: GridCell, distance: Int): Set<GridCell> {
        val ret = HashSet<GridCell>()
        for (x in -distance..distance) {
            for (y in max(-distance, -x - distance)..min(distance, -x + distance)) {
                val z = -x - y
                val tempCoordinate =
                    CubeCoordinate.fromCoordinates(from.getCoordinate().gridX + x, from.getCoordinate().gridZ + z)
                val tempCell = hexagonalGrid.getByCubeCoordinate(tempCoordinate)
                if (tempCell != null) ret += tempCell
            }
        }
        return ret
    }

    override fun rotateHexagon(
        centerCell: GridCell,
        targetCell: GridCell,
        rotationDirection: RotationDirection
    ): GridCell? {
        val diffX = targetCell.getCoordinate().gridX - centerCell.getCoordinate().gridX
        val diffZ = targetCell.getCoordinate().gridZ - centerCell.getCoordinate().gridZ
        val diffCoord = CubeCoordinate.fromCoordinates(diffX, diffZ)
        val rotatedCoord = rotationDirection.calculateRotation(diffCoord)
        val resultCoord = CubeCoordinate.fromCoordinates(
            centerCell.getCoordinate().gridX + rotatedCoord.gridX,
            centerCell.getCoordinate().gridZ + rotatedCoord.gridZ
        ) // 0, x,
        return hexagonalGrid.getByCubeCoordinate(resultCoord)
    }

    override fun calculateRingFrom(centerCoordinate: CubeCoordinate, radius: Int): Set<GridCell> {
        val result = HashSet<GridCell>()

        var currentCoordinate = CubeCoordinate.fromCoordinates(
            centerCoordinate.gridX - radius,
            centerCoordinate.gridZ + radius
        )

        for (i in 0 until 6) {
            for (j in 0 until radius) {
                currentCoordinate = hexagonalGrid.getNeighborCoordinateByIndex(currentCoordinate, i)
                val currentCell = hexagonalGrid.getByCubeCoordinate(currentCoordinate)
                if(currentCell != null) result += currentCell
            }
        }

        return result
    }

    override fun drawLine(from: GridCell, to: GridCell): List<GridCell> {
        val distance = calculateDistanceBetween(from, to)
        if (distance == 0) {
            return emptyList()
        }
        val results = ArrayList<GridCell>(distance + 1)
        for (i in 0..distance) {
            val interpolatedCoordinate = cubeLinearInterpolate(
                from.getCoordinate(),
                to.getCoordinate(), 1.0 / distance * i
            )
            val currentCell = hexagonalGrid.getByCubeCoordinate(interpolatedCoordinate)
            if(currentCell != null) results += currentCell
        }
        return results
    }

    private fun cubeLinearInterpolate(from: CubeCoordinate, to: CubeCoordinate, sample: Double): CubeCoordinate {
        return roundToCubeCoordinate(
            linearInterpolate(from.gridX, to.gridX, sample),
            linearInterpolate(from.gridY, to.gridY, sample),
            linearInterpolate(from.gridZ, to.gridZ, sample)
        )
    }

    private fun linearInterpolate(from: Int, to: Int, sample: Double): Double {
        return from + (to - from) * sample
    }

    private fun roundToCubeCoordinate(gridX: Double, gridY: Double, gridZ: Double): CubeCoordinate {
        var rx = round(gridX).toInt()
        val ry = round(gridY).toInt()
        var rz = round(gridZ).toInt()

        val differenceX = abs(rx - gridX)
        val differenceY = abs(ry - gridY)
        val differenceZ = abs(rz - gridZ)

        if (differenceX > differenceY && differenceX > differenceZ) {
            rx = -ry - rz
        } else if (differenceY <= differenceZ) {
            rz = -rx - ry
        }
        return CubeCoordinate.fromCoordinates(rx, rz)
    }
}
