package org.hexworks.jmixite.core.grid.builder;

import org.hexworks.jmixite.core.grid.HexagonalGrid;
import org.hexworks.jmixite.core.grid.layout.GridLayoutStrategy;
import org.hexworks.jmixite.core.grid.layout.HexagonalGridLayout;
import org.hexworks.jmixite.core.geometry.HexagonOrientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HexagonalGridBuilderTest 
{        
    private final int GRID_HEIGHT = 9;
    private final int GRID_WIDTH = 9;
    private final double RADIUS = 30.0;
    private final HexagonalGridLayout GRID_LAYOUT = HexagonalGridLayout.RECTANGULAR;
    private final GridLayoutStrategy GRID_LAYOUT_STRATEGY = HexagonalGridLayout.RECTANGULAR.getGridLayoutStrategy();
    private final HexagonOrientation ORIENTATION = HexagonOrientation.FLAT_TOP;

    private final HexagonalGridBuilder target =
            new HexagonalGridBuilder()
                    .setGridHeight(GRID_HEIGHT)
                    .setGridLayout(GRID_LAYOUT.getGridLayoutStrategy())
                    .setGridWidth(GRID_WIDTH)
                    .setOrientation(ORIENTATION)
                    .setRadius(RADIUS);

    @Test
    public void shouldContainProperValuesWhenGettersAreCalled() {
        assertEquals(GRID_HEIGHT, target.getGridHeight());
        assertEquals(GRID_WIDTH, target.getGridWidth());
        assertEquals(GRID_LAYOUT_STRATEGY, target.getGridLayoutStrategy());
        assertEquals(RADIUS, target.getRadius());
        assertNotNull(target.getGridSpec());
    }

    @Test
    public void shouldFailGettingSharedHexagonDataWhenOrientationIsNull() {
        assertThrows(IllegalStateException.class,
                () -> target.setRadius(0.0)
                        .build().getGridSpec());
    }


    @Test
    public void shouldFailBuildWhenSizeIsNotCompatibleWithLayout() {
        assertThrows(IllegalStateException.class,
                () -> target.setGridLayout(HexagonalGridLayout.TRIANGULAR.getGridLayoutStrategy())
                        .setGridHeight(4)
                        .build());
    }

    @Test
    public void shouldReturnProperOrientationWhenGetOrientationIsCalled() {
        assertEquals(ORIENTATION, target.getOrientation());
    }

    @Test
    public void shouldBuildWhenProperParametersArePresent() {
        HexagonalGrid grid = target.build();
        assertNotNull(grid);
    }

}
