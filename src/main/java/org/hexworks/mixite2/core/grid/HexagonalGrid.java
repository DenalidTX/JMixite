package org.hexworks.mixite2.core.grid

import org.hexworks.mixite2.core.geometry.CubeCoordinate
import org.hexworks.mixite2.core.geometry.Hexagon
import org.hexworks.mixite2.core.geometry.Point


/**
 *
 *
 * Represents a hexagonal grid. Use [HexagonalGridBuilder] to generate a
 * ready-to-use grid. This interface contains all common functionality for dealing with
 * Hexagons. See [HexagonalGridCalculator] for more advanced features.
 *
 *
 *
 * This [HexagonalGrid] uses an cube (trapezoidal) coordinate system for easier
 * computation. This means that apart from the X axis a diagonal axis is used instead of
 * the vertical Y axis.
 *
 */
interface HexagonalGrid {

    /**
     * Returns this HexagonalGrid's GridData.
     *
     * @return grid data
     */
    val gridSpec: GridSpec

    /**
     * Returns all [Hexagon]s contained in this grid.
     *
     * @return hexagons
     */
    val cells: HashSet<GridCell>

    /**
     * Returns all [Hexagon]s contained in the given cube coordinate range.
     * If the range contains coordinates which are not part of the grid they will be ignored.
     *
     * @param from from
     * @param to to
     *
     * @return [Hexagon]s in the given range.
     */
    fun getHexagonsByCubeRange(from: CubeCoordinate, to: CubeCoordinate): Iterable<GridCell>

    /**
     * Returns all [Hexagon]s contained in the given offset coordinate range.
     * If the range contains coordinates which are not part of the grid they will be ignored.
     *
     * @param gridXFrom from x inclusive
     * @param gridXTo to x inclusive
     * @param gridYFrom from z inclusive
     * @param gridYTo to z inclusive
     *
     * @return [Hexagon]s in the given range.
     */
    fun getHexagonsByOffsetRange(gridXFrom: Int, gridXTo: Int, gridYFrom: Int, gridYTo: Int): Iterable<GridCell>

    /**
     * Tells whether the given cube coordinate is on the grid or not.
     * If you want to look up by offset coordinate use [CoordinateConverter].
     *
     * @param coordinate coord
     *
     * @return is it on the grid?
     */
    fun containsCubeCoordinate(coordinate: CubeCoordinate): Boolean

    /**
     * Returns a [Hexagon] by its cube coordinate.
     *
     * @param coordinate coord
     *
     * @return Maybe with a Hexagon if it is present
     */
    fun getByCubeCoordinate(coordinate: CubeCoordinate): GridCell?

    /**
     * Returns a [Hexagon] by a pixel coordinate.
     * *Please note* that all pixel coordinates are relative to
     * the containing [HexagonalGrid].
     *
     * @param coordinateX pixel coordinateX coordinate
     * @param coordinateY pixel coordinateY coordinate
     *
     * @return Maybe with a Hexagon if it is present
     */
    fun getByPixelCoordinate(pixelCoord: Point): GridCell?

    /**
     * Returns the coordinate of a neighbor of a Hexagon by its neighbor index.
     *
     * @return CubeCoordinate
     */
    fun getNeighborCoordinateByIndex(coordinate: CubeCoordinate, index: Int): CubeCoordinate

    /**
     * Returns a neighbor of a Hexagon by its neighbor index.
     *
     * @return neighbor or empty Maybe if not applicable
     */
    fun getNeighborByIndex(hexagon: GridCell, index: Int): GridCell?

    /**
     * Returns all neighbors of a [Hexagon].
     *
     * @param hexagon [Hexagon]
     *
     * @return the [Hexagon]'s neighbors
     */
    fun getNeighborsOf(hexagon: GridCell): Collection<GridCell>

}
