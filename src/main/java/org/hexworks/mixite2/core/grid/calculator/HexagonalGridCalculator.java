package org.hexworks.mixite2.core.grid.calculator;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.grid.HexagonalGrid;
import org.hexworks.mixite2.core.geometry.Hexagon;
import org.hexworks.mixite2.core.geometry.RotationDirection;
import org.hexworks.mixite2.core.grid.GridCell;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


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
public interface HexagonalGridCalculator
{
    /**
     * Calculates the distance (in hexagons) between two [Hexagon] objects on the grid.
     *
     * @param first hex 0
     * @param second hex 1
     *
     * @return distance
     */
    int calculateDistanceBetween(GridCell first, GridCell second);

    /**
     * Returns all [Hexagon]s which are within `distance` (inclusive) from the [Hexagon].
     *
     * @param from [Hexagon]
     * @param distance distance
     *
     * @return [Hexagon]s within distance (inclusive)
     */
    Collection<GridCell> calculateMovementRangeFrom(GridCell from, int distance);

    /**
     * Returns the Hexagon on the grid which is at the point resulted by rotating the `targetHex`'s
     * coordinates around the `originalHex` by `rotationDirection` degrees.
     *
     * @param centerCell center hex
     * @param targetCell hex to rotate
     * @param rotationDirection direction of the rotation
     *
     * @return result
     */
    Optional<GridCell> rotateHexagon(GridCell centerCell, GridCell targetCell, RotationDirection rotationDirection);


    /**
     * Returns the [Set] of [GridCell]s which are `radius` distance
     * from `centerHexagon`.
     *
     * @param centerCoordinate center
     * @param radius radius
     *
     * @return Set of hexagons or empty set if not applicable
     */
    Collection<GridCell> calculateRingFrom(CubeCoordinate centerCoordinate, int radius);

    /**
     * Returns a [List] of [Hexagon]s which must be traversed in the
     * given order to go from the `from` Hexagon to the `to` Hexagon.
     *
     * @param from starting hexagon
     * @param to target hexagon
     *
     * @return List of hexagons containing the line
     */
    List<GridCell> drawLine(GridCell from, GridCell to);
}
