package org.hexworks.mixite2.core.grid.layout

import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.grid.GridSpec

class TrapezoidGridLayoutStrategy : GridLayoutStrategy() {

    override fun fetchGridCoordinates(gridSpec: GridSpec): Iterable<CubeCoordinate> {
        val coords = ArrayList<CubeCoordinate>(gridSpec.getGridHeight() * gridSpec.getGridWidth())
        for (gridZ in 0 until gridSpec.getGridHeight()) {
            for (gridX in 0 until gridSpec.getGridWidth()) {
                coords.add(CubeCoordinate.fromCoordinates(gridX, gridZ))
            }
        }
        return coords
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        return checkCommonCase(gridHeight, gridWidth)
    }

    override fun getName(): String {
        return "TRAPEZOID"
    }
}
