package org.hexworks.mixite2.core.grid.layout

import org.hexworks.mixite2.core.geometry.CoordinateConverter
import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.grid.GridSpec

class RectangularGridLayoutStrategy : GridLayoutStrategy() {

    override fun fetchGridCoordinates(gridSpec: GridSpec): Iterable<CubeCoordinate> {
        val coords = ArrayList<CubeCoordinate>( gridSpec.getGridHeight() * gridSpec.getGridWidth())
        for (y in 0 until gridSpec.getGridHeight()) {
            for (x in 0 until gridSpec.getGridWidth()) {
                val gridX = CoordinateConverter.convertOffsetCoordinatesToCubeX(x, y, gridSpec.getOrientation())
                val gridZ = CoordinateConverter.convertOffsetCoordinatesToCubeZ(x, y, gridSpec.getOrientation())
                coords.add(CubeCoordinate.fromCoordinates(gridX, gridZ))
            }
        }
        return coords
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        return checkCommonCase(gridHeight, gridWidth)
    }

    override fun getName(): String {
        return "RECTANGULAR"
    }
}
