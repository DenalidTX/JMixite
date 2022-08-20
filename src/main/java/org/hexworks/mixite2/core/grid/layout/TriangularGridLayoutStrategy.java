package org.hexworks.mixite2.core.grid.layout

import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.grid.GridSpec

class TriangularGridLayoutStrategy : GridLayoutStrategy() {

    override fun fetchGridCoordinates(gridSpec: GridSpec): Iterable<CubeCoordinate> {
        val gridSize = gridSpec.getGridHeight()
        val coords = ArrayList<CubeCoordinate>(gridSize * (gridSize + 1) / 2)
        for (gridZ in 0 until gridSize) {
            val endX = gridSize - gridZ
            for (gridX in 0 until endX) {
                coords.add(CubeCoordinate.fromCoordinates(gridX, gridZ))
            }
        }
        return coords
    }

    override fun checkParameters(gridHeight: Int, gridWidth: Int): Boolean {
        val superResult = checkCommonCase(gridHeight, gridWidth)
        val result = gridHeight == gridWidth
        return superResult && result
    }

    override fun getName(): String {
        return "TRIANGULAR"
    }
}
