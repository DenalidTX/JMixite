package org.hexworks.mixite2.core.grid.layout;

import org.hexworks.mixite2.core.geometry.HexagonOrientation;
import org.hexworks.mixite2.core.grid.builder.HexagonalGridBuilder;

class GridLayoutStrategyTestUtil
{

    private static final double RADIUS = 30.0;
    private static final int GRID_WIDTH = 3;
    private static final int GRID_HEIGHT = 3;
    private static final HexagonOrientation ORIENTATION = HexagonOrientation.POINTY_TOP;

    public static HexagonalGridBuilder fetchDefaultBuilder()
    {
        return new HexagonalGridBuilder()
                .setGridHeight(GRID_HEIGHT)
                .setGridWidth(GRID_WIDTH)
                .setRadius(RADIUS)
                .setOrientation(ORIENTATION);
    }
}
