package org.hexworks.mixite2.core.grid.layout

import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.geometry.HexagonOrientation
import org.hexworks.mixite2.core.grid.GridSpec
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.round

class HexagonalGridLayoutStrategy : GridLayoutStrategy() {

    override fun fetchGridCoordinates(gridSpec: GridSpec): Iterable<CubeCoordinate> {
        val gridSize = gridSpec.getGridHeight()
        val coords = ArrayList<CubeCoordinate>(1 + (gridSize * gridSize * 6 - 6) / 8) // TODO cell count owned by the builder
        var startX = if (HexagonOrientation.FLAT_TOP.equals(gridSpec.getOrientation())) floor(gridSize / 2.0).toInt() else round(gridSize / 4.0).toInt()
        val hexRadius = floor(gridSize / 2.0).toInt()
        val minX = startX - hexRadius
        var y = 0
        while (y < gridSize) {
            val distanceFromMid = abs(hexRadius - y)
            for (x in max(startX, minX)..max(startX, minX) + hexRadius + hexRadius - distanceFromMid) {
                val gridZ = if (HexagonOrientation.FLAT_TOP.equals(gridSpec.getOrientation())) y - floor(gridSize / 4.0).toInt() else y
                coords.add(CubeCoordinate.fromCoordinates(x, gridZ))
            }
            startX--
            y++
        }
        return coords
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        val superResult = checkCommonCase(gridHeight, gridWidth)
        val result = gridHeight == gridWidth && abs(gridHeight % 2) == 1
        return result && superResult
    }

    override fun getName(): String {
        return "HEXAGONAL"
    }

}
