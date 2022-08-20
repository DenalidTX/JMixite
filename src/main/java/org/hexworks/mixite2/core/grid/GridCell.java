package org.hexworks.mixite2.core.grid

import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.geometry.Hexagon
import org.hexworks.mixite2.core.geometry.Point

class GridCell(
    private val coordinate: CubeCoordinate,
    private val hexagon: Hexagon) {

    fun getCoordinate() : CubeCoordinate {
        return coordinate
    }

    fun getHexagon() : Hexagon {
        return hexagon
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as GridCell

        if (coordinate != other.coordinate) return false

        return true
    }

    fun equals(otherGridX: Int, otherGridZ: Int): Boolean {
        return coordinate.equals(otherGridX, otherGridZ)
    }

    override fun hashCode(): Int {
        return coordinate.hashCode()
    }
}