package org.hexworks.mixite2.core.grid.calculator

import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.grid.HexagonalGrid
import org.hexworks.mixite2.core.geometry.Hexagon
import org.hexworks.mixite2.core.geometry.RotationDirection
import org.hexworks.mixite2.core.grid.GridCell


/**
 * Supports advanced operations on a [HexagonalGrid].
 * Operations supported:
 *
 *  * Calculating distance between 2 [Hexagon]s.
 *  * Calculating movement range from a [Hexagon] using an arbitrary distance.
 *
 * *Not implemented yet, but are on the roadmap:*
 *
 *  * Calculating movement range with obstacles
 *  * Calculating field of view
 *  * Path finding between two [Hexagon]s (using obstacles)
 *
 */
interface HexagonalGridCalculator {

    /**
     * The hexagonal grid used for the calculations
     */
    val hexagonalGrid: HexagonalGrid

    /**
     * Calculates the distance (in hexagons) between two [Hexagon] objects on the grid.
     *
     * @param hex0 hex 0
     * @param hex1 hex 1
     *
     * @return distance
     */
    fun calculateDistanceBetween(first: GridCell, second: GridCell): Int

    /**
     * Returns all [Hexagon]s which are within `distance` (inclusive) from the [Hexagon].
     *
     * @param hexagon [Hexagon]
     * @param distance distance
     *
     * @return [Hexagon]s within distance (inclusive)
     */
    fun calculateMovementRangeFrom(from: GridCell, distance: Int): Set<GridCell>

    /**
     * Returns the Hexagon on the grid which is at the point resulted by rotating the `targetHex`'s
     * coordinates around the `originalHex` by `rotationDirection` degrees.
     *
     * @param originalHex center hex
     * @param targetHex hex to rotate
     * @param rotationDirection direction of the rotation
     *
     * @return result
     */
    fun rotateHexagon(centerCell: GridCell, targetCell: GridCell, rotationDirection: RotationDirection): GridCell?


    /**
     * Returns the [Set] of [GridCell]s which are `radius` distance
     * from `centerHexagon`.
     *
     * @param centerCell center
     * @param radius radius
     *
     * @return Set of hexagons or empty set if not applicable
     */
    fun calculateRingFrom(centerCoordinate: CubeCoordinate, radius: Int): Set<GridCell>

    /**
     * Returns a [List] of [Hexagon]s which must be traversed in the
     * given order to go from the `from` Hexagon to the `to` Hexagon.
     *
     * @param from starting hexagon
     * @param to target hexagon
     *
     * @return List of hexagons containing the line
     */
    fun drawLine(from: GridCell, to: GridCell): List<GridCell>
}
