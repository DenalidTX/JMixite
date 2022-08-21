package org.hexworks.jmixite.core.geometry;

import static org.hexworks.jmixite.core.geometry.CubeCoordinate.fromCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hexworks.jmixite.core.grid.GridSpec;
import org.hexworks.jmixite.core.grid.layout.HexagonalGridLayout;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class HexagonTest 
{
    private final double TEST_RADIUS = 10.0;
    private final int TEST_GRID_X = 2;
    private final int TEST_GRID_Z = 3;
    private final int TEST_GRID_Y = -5;
    private final int EXPECTED_POINTY_CENTER_X = 69;
    private final int EXPECTED_FLAT_CENTER_X = 40;
    private final int EXPECTED_POINTY_CENTER_Y = 55;
    private final int EXPECTED_FLAT_CENTER_Y = 78;
    private final GridSpec TEST_POINTY_DATA = new GridSpec(
            HexagonOrientation.POINTY_TOP, 
            HexagonalGridLayout.RECTANGULAR.getGridLayoutStrategy(),
            TEST_RADIUS, 
            1, 1);
    private final GridSpec TEST_FLAT_DATA = new GridSpec(
            HexagonOrientation.FLAT_TOP,
            HexagonalGridLayout.RECTANGULAR.getGridLayoutStrategy(),
            TEST_RADIUS, 
            1, 1);
    private final CubeCoordinate TEST_COORDINATE = fromCoordinates(TEST_GRID_X, TEST_GRID_Z);
    private final List<Point> EXPECTED_FLAT_POINTS = Arrays.asList(
            Point.fromPosition(50.0, 78.0),
            Point.fromPosition(45.0, 87.0),
            Point.fromPosition(35.0, 87.0),
            Point.fromPosition(30.0, 78.0),
            Point.fromPosition(35.0, 69.0),
            Point.fromPosition(45.0, 69.0));
    private final List<Point> EXPECTED_POINTY_POINTS =  Arrays.asList(
            Point.fromPosition(78.0, 60.0),
            Point.fromPosition(69.0, 65.0),
            Point.fromPosition(61.0, 60.0),
            Point.fromPosition(61.0, 50.0),
            Point.fromPosition(69.0, 45.0),
            Point.fromPosition(78.0, 50.0));

    private final Hexagon pointy_target = new Hexagon(TEST_COORDINATE, TEST_POINTY_DATA);
    private final Hexagon flat_target = new Hexagon(TEST_COORDINATE, TEST_FLAT_DATA);

    @Test
    public void shouldHaveProperPointsWhenPointy() 
    {
        for (int i=0; i<6; i++) 
        {
            assertEquals((long) EXPECTED_POINTY_POINTS.get(i).coordinateX(), Math.round(pointy_target.points.get(i).coordinateX()));
            assertEquals((long) EXPECTED_POINTY_POINTS.get(i).coordinateY(), Math.round(pointy_target.points.get(i).coordinateY()));
        }
    }

    @Test
    public void shouldHaveProperPointsWhenFlat() 
    {
        for (int i=0; i<6; i++)
        {
            assertEquals((long) EXPECTED_FLAT_POINTS.get(i).coordinateX(), Math.round(flat_target.points.get(i).coordinateX()));
            assertEquals((long) EXPECTED_FLAT_POINTS.get(i).coordinateY(), Math.round(flat_target.points.get(i).coordinateY()));
        }
    }

    @Test
    public void shouldReturnProperXCoordinateWhenGetGridXIsCalled() {
        assertEquals(TEST_GRID_X, pointy_target.cubeCoordinate().gridX());
    }

    @Test
    public void shouldReturnProperXCoordinateWhenGetGridYIsCalled() {
        assertEquals(TEST_GRID_Y, pointy_target.cubeCoordinate().gridY());
    }

    @Test
    public void shouldReturnProperXCoordinateWhenGetGridZIsCalled() {
        assertEquals(TEST_GRID_Z, pointy_target.cubeCoordinate().gridZ());
    }

    @Test
    public void shouldReturnProperCenterXCoordinateWhenGetCenterXIsCalledWithPointyHexagons() {
        assertEquals(EXPECTED_POINTY_CENTER_X, Math.round(pointy_target.getCenter().coordinateX()));
    }

    @Test
    public void shouldReturnProperCenterXCoordinateWhenGetCenterXIsCalledWithFlatHexagons() {
        assertEquals(EXPECTED_FLAT_CENTER_X, Math.round(flat_target.getCenter().coordinateX()));
    }

    @Test
    public void shouldReturnProperCenterYCoordinateWhenGetCenterYIsCalledWithPointyHexagons() {
        assertEquals(EXPECTED_POINTY_CENTER_Y, Math.round(pointy_target.getCenter().coordinateY()));
    }

    @Test
    public void shouldReturnProperCenterYCoordinateWhenGetCenterYIsCalledWithFlatHexagons() {
        assertEquals(EXPECTED_FLAT_CENTER_Y, Math.round(flat_target.getCenter().coordinateY()));
    }

    @Test
    public void shouldBeEqualToItself() {
        assertEquals(pointy_target, pointy_target);
    }
}
