package org.hexworks.jmixite.core.grid;

import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import org.hexworks.jmixite.core.geometry.Point;

import java.util.Collection;
import java.util.Optional;


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
public interface HexagonalGrid
{
    /**
     * Returns all [Hexagon]s contained in the given cube coordinate range.
     * If the range contains coordinates which are not part of the grid they will be ignored.
     *
     * @param from from
     * @param to to
     *
     * @return [Hexagon]s in the given range.
     */
    Collection<GridCell> getHexagonsByCubeRange(CubeCoordinate from, CubeCoordinate to);

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
    Collection<GridCell> getHexagonsByOffsetRange(int gridXFrom, int gridXTo, int gridYFrom, int gridYTo);

    /**
     * Tells whether the given cube coordinate is on the grid or not.
     * If you want to look up by offset coordinate use [CoordinateConverter].
     *
     * @param coordinate coord
     *
     * @return is it on the grid?
     */
    boolean containsCubeCoordinate(CubeCoordinate coordinate);

    /**
     * Returns a [Hexagon] by its cube coordinate.
     *
     * @param coordinate coord
     *
     * @return Maybe with a Hexagon if it is present
     */
    Optional<GridCell> getByCubeCoordinate(CubeCoordinate coordinate);

    /**
     * Returns a [Hexagon] by a pixel coordinate.
     * *Please note* that all pixel coordinates are relative to
     * the containing [HexagonalGrid].
     *
     * @param pixelCoord The coordinate to look for within this grid.
     *
     * @return Maybe with a Hexagon if it is present
     */
    Optional<GridCell> getByPixelCoordinate(Point pixelCoord);

    /**
     * Returns the coordinate of a neighbor of a Hexagon by its neighbor index.
     *
     * @return CubeCoordinate
     */
    CubeCoordinate getNeighborCoordinateByIndex(CubeCoordinate coordinate, int index);

    /**
     * Returns a neighbor of a Hexagon by its neighbor index.
     *
     * @return neighbor or empty Maybe if not applicable
     */
    Optional<GridCell> getNeighborByIndex(GridCell hexagon, int index);

    /**
     * Returns all neighbors of a [Hexagon].
     *
     * @param hexagon [Hexagon]
     *
     * @return the [Hexagon]'s neighbors
     */
    Collection<GridCell> getNeighborsOf(GridCell hexagon);

    GridSpec getGridSpec();

    Collection<GridCell> getCells();
}
