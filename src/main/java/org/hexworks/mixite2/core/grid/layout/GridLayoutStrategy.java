package org.hexworks.mixite2.core.grid.layout;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.grid.GridSpec;


/**
 * Represents the method of creating a [org.hexworks.mixite.core.api.HexagonalGrid] corresponding to a given shape.
 */
public interface GridLayoutStrategy
{

    /**
     * Fetches a monotonically increasing (from left to right, top to bottom) Set of
     * grid coordinates corresponding to the shape of the requested grid layout.
     *
     * @param gridSpec Configuration object for the grid.
     *
     * @return object for iterating through all coordinates
     */
    Iterable<CubeCoordinate> fetchGridCoordinates(GridSpec gridSpec);

    /**
     * Checks whether the supplied parameters are valid for the given strategy.
     * *For example a hexagonal grid layout only works if the width equals to the height*
     *
     * @param gridHeight height
     * @param gridWidth width
     *
     * @return valid?
     */
    boolean checkParameters(int gridHeight, int gridWidth);

    default boolean checkCommonCase(int gridHeight, int gridWidth)
    {
        return gridHeight > 0 && gridWidth > 0;
    }

    /**
     * Returns the name of this strategy.
     *
     * @return name
     */
    String getName();

}
