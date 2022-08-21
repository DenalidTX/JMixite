package org.hexworks.jmixite.core.grid;

import org.hexworks.jmixite.core.grid.layout.GridLayoutStrategy;
import org.hexworks.jmixite.core.grid.layout.HexagonalGridLayout;
import org.hexworks.jmixite.core.geometry.HexagonOrientation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GridDataTest {
    
    private final double RADIUS = 30.0;
    private final int GRID_WIDTH = 30;
    private final int GRID_HEIGHT = 30;
    private HexagonOrientation ORIENTATION = HexagonOrientation.FLAT_TOP;
    private GridLayoutStrategy GRID_LAYOUT = HexagonalGridLayout.RECTANGULAR.getGridLayoutStrategy();

    @Test
    public void shouldProperlyReturnRadiusWhenGetRadiusIsCalled() {
        GridSpec target = new GridSpec(ORIENTATION, GRID_LAYOUT, RADIUS, GRID_WIDTH, GRID_HEIGHT);
        assertEquals(RADIUS, target.getOuterRadius());
    }

    @Test
    public void shouldProperlyCalculateWidthWithPointyHexagonsWhenGetWidthIsCalled() {
        GridSpec target = createWithPointy();
        double expectedWidth = Math.sqrt(3.0) * RADIUS;
        double actualWidth = target.getHexagonWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    public void shouldProperlyCalculateWidthWithFlatHexagonsWhenGetWidthIsCalled() {
        GridSpec target = createWithFlat();
        double expectedWidth = RADIUS * 3 / 2;
        double actualWidth = target.getHexagonWidth();
        assertEquals(expectedWidth, actualWidth);
    }

    @Test
    public void shouldProperlyCalculateHeightWithPointyHexagonsWhenGetHeightIsCalled() {
        GridSpec target = createWithPointy();
        double expectedHeight = RADIUS * 3 / 2;
        double actualHeight = target.getHexagonHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    public void shouldProperlyCalculateHeightWithFlatHexagonsWhenGetHeightIsCalled() {
        GridSpec target = createWithFlat();
        double expectedHeight = Math.sqrt(3.0) * RADIUS;
        double actualHeight = target.getHexagonHeight();
        assertEquals(expectedHeight, actualHeight);
    }

    @Test
    public void shouldReturnProperOrientationWhenGetOrientationIsCalled() {
        GridSpec target = new GridSpec(ORIENTATION, GRID_LAYOUT, RADIUS, GRID_WIDTH, GRID_HEIGHT);
        assertEquals(ORIENTATION, target.getOrientation());
    }

    @Test
    public void shouldReturnProperCoordinateOffsetWhengetCoordinateOffsetIsCalled() {
        GridSpec target = new GridSpec(ORIENTATION, GRID_LAYOUT, RADIUS, GRID_WIDTH, GRID_HEIGHT);
        assertEquals(ORIENTATION.getCoordinateOffset(), target.getOrientation().getCoordinateOffset());
    }

    private GridSpec createWithPointy() {
        return new GridSpec(HexagonOrientation.POINTY_TOP, GRID_LAYOUT, RADIUS, GRID_WIDTH, GRID_HEIGHT);
    }

    private GridSpec createWithFlat() {
        return new GridSpec(HexagonOrientation.FLAT_TOP, GRID_LAYOUT, RADIUS, GRID_WIDTH, GRID_HEIGHT);
    }
}
