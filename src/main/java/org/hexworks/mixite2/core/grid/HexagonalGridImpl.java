package org.hexworks.mixite2.core.grid

import org.hexworks.mixite2.core.geometry.CoordinateConverter
import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.geometry.Hexagon
import org.hexworks.mixite2.core.geometry.Point
import kotlin.math.abs

class HexagonalGridImpl(override val gridSpec: GridSpec) : HexagonalGrid {

    override val cells: HashSet<GridCell> = HashSet()

    init {
        for (cubeCoordinate in gridSpec.getGridLayout().fetchGridCoordinates(gridSpec)) {
            cells.add(GridCell(cubeCoordinate, Hexagon(cubeCoordinate, gridSpec)))
        }
    }

    override fun getHexagonsByCubeRange(from: CubeCoordinate, to: CubeCoordinate): List<GridCell> {
        val coordinates = ArrayList<GridCell>(abs(from.gridZ - to.gridZ) + abs(from.gridX - to.gridX))

        for (gridZ in from.gridZ .. to.gridZ) {
            for (gridX in from.gridX .. to.gridX) {
                // TODO: Verify that this works and that the names are accurate.
                val coord = getByGridCoordinate(gridX, gridZ)
                if (coord != null) {
                    coordinates.add(coord)
                }
            }
        }

        return coordinates
    }

    override fun getHexagonsByOffsetRange(gridXFrom: Int, gridXTo: Int, gridYFrom: Int, gridYTo: Int): List<GridCell> {
        val coordinates = ArrayList<GridCell>()

        for (gridX in gridXFrom..gridXTo) {
            for (gridY in gridYFrom..gridYTo) {
                // TODO: Verify that this works and that the names are accurate.
                val cubeX = CoordinateConverter.convertOffsetCoordinatesToCubeX(gridX, gridY, gridSpec.getOrientation())
                val cubeZ = CoordinateConverter.convertOffsetCoordinatesToCubeZ(gridX, gridY, gridSpec.getOrientation())
                val coord = getByGridCoordinate(cubeX, cubeZ)
                if (coord != null) {
                    coordinates.add(coord)
                }
            }
        }

        return coordinates
    }

    override fun containsCubeCoordinate(coordinate: CubeCoordinate): Boolean {
        return this.getByCubeCoordinate(coordinate) != null
    }

    override fun getByCubeCoordinate(coordinate: CubeCoordinate): GridCell? =
        // TODO: Verify that this has the desired effect. Is 'cube coordinate' the same as 'grid coordinate'?
        getByGridCoordinate(coordinate.gridX, coordinate.gridZ)

    private fun getByGridCoordinate(gridX: Int, gridZ: Int): GridCell? =
        cells.firstOrNull { gridCell: GridCell -> gridCell.equals(gridX, gridZ) }

    override fun getByPixelCoordinate(pixelCoord: Point): GridCell? {
        var estimatedGridX = (pixelCoord.coordinateX / gridSpec.getHexagonWidth()).toInt()
        var estimatedGridZ = (pixelCoord.coordinateY / gridSpec.getHexagonHeight()).toInt()
        estimatedGridX = CoordinateConverter.convertOffsetCoordinatesToCubeX(
            estimatedGridX,
            estimatedGridZ,
            gridSpec.getOrientation()
        )
        estimatedGridZ = CoordinateConverter.convertOffsetCoordinatesToCubeZ(
            estimatedGridX,
            estimatedGridZ,
            gridSpec.getOrientation()
        )

        // We might be off-grid, so we can't just choose the cell that ought to exist.
        // Instead, estimate the cubic coordinates of the point (done above), determine
        // the valid neighbors, and find the smallest distance among those. However, if
        // the estimated coordinate is within the grid's inner radius, just use that and
        // don't bother about the neighbors.

        val estimatedCell = getByGridCoordinate(estimatedGridX, estimatedGridZ)
        var closestCell: GridCell? = null

        // It's unlikely that we will have a null estimate, but check just in case.
        if (estimatedCell != null) {

            val estCellDistance = pixelCoord.distanceFrom(estimatedCell.getHexagon().getCenter())
            if(estCellDistance <= gridSpec.innerRadius){
                closestCell = estimatedCell
            }
            else {
                // Use the distance as the key so that it's easy to look up the cell.
                var distances = HashMap<Double, GridCell>()

                // If we didn't find the estimated cell in our real grid, look for neighbors.
                for (neighborIndex in NEIGHBORS.indices) {
                    var neighbor = getByGridCoordinate(
                        NEIGHBORS[neighborIndex][NEIGHBOR_X_INDEX] + estimatedCell.getCoordinate().gridX,
                        NEIGHBORS[neighborIndex][NEIGHBOR_Z_INDEX] + estimatedCell.getCoordinate().gridZ
                    )
                    if (neighbor != null) {
                        distances[pixelCoord.distanceFrom(neighbor.getHexagon().getCenter())] = neighbor
                    }
                }

                if(!distances.isEmpty()) {
                    val minDist = distances.keys.minOrNull()
                    if(minDist != null) {
                        closestCell = distances[minDist]
                    }
                }
            }
        }

        return closestCell
    }

    private fun _getNeighborByIndex(hexagon: GridCell, index: Int) =
        CubeCoordinate.fromCoordinates(
            hexagon.getCoordinate().gridX + NEIGHBORS[index][NEIGHBOR_X_INDEX],
            hexagon.getCoordinate().gridZ + NEIGHBORS[index][NEIGHBOR_Z_INDEX]
        )

    override fun getNeighborCoordinateByIndex(coordinate: CubeCoordinate, index: Int) =
        CubeCoordinate.fromCoordinates(
            coordinate.gridX + NEIGHBORS[index][NEIGHBOR_X_INDEX],
            coordinate.gridZ + NEIGHBORS[index][NEIGHBOR_Z_INDEX]
        )

    override fun getNeighborByIndex(hexagon: GridCell, index: Int) =
        getByCubeCoordinate(_getNeighborByIndex(hexagon, index))

    override fun getNeighborsOf(hexagon: GridCell): Collection<GridCell> {
        val neighbors = HashSet<GridCell>()
        for (i in NEIGHBORS.indices) {
            val retHex = getNeighborByIndex(hexagon, i)
            if (retHex != null) {
                neighbors.add(retHex)
            }
        }
        return neighbors
    }

    companion object {

        private val NEIGHBORS = arrayOf(
            intArrayOf(+1, 0),
            intArrayOf(+1, -1),
            intArrayOf(0, -1),
            intArrayOf(-1, 0),
            intArrayOf(-1, +1),
            intArrayOf(0, +1)
        )
        private const val NEIGHBOR_X_INDEX = 0
        private const val NEIGHBOR_Z_INDEX = 1
    }
}
