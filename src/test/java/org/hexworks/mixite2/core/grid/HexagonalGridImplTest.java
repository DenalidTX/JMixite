package org.hexworks.mixite2.core.grid;

import org.hexworks.mixite2.core.geometry.*;

import static org.hexworks.mixite2.core.TestUtils.contentsEqual;
import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromCoordinates;
import static org.junit.jupiter.api.Assertions.*;

import org.hexworks.mixite2.core.grid.builder.HexagonalGridBuilder;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.*;

/**
 * This class does not actually run its own tests! This is a base class,
 * and the subclasses each run the inherited tests.
 */
abstract class HexagonalGridImplTest
{        
    protected final double RADIUS = 30;
    protected final int GRID_WIDTH = 10;
    protected final int GRID_HEIGHT = 10;
    protected final int GRID_X_FROM = 2;
    protected final int GRID_X_TO = 4;
    protected final int GRID_Z_FROM = 3;
    protected final int GRID_Z_TO = 5;
    protected final HexagonOrientation ORIENTATION = HexagonOrientation.POINTY_TOP;

    protected HexagonalGridBuilder builder = getBuilder();
    protected HexagonalGrid target = builder.build();

    protected static final CoordinateConverter coordConverter = new CoordinateConverter();

    abstract public HexagonalGridBuilder getBuilder();

    @Test
    abstract public void shouldReturnProperHexagonsWhenEachHexagonIsTestedInAGivenGrid();

    @Test
    public void shouldReturnProperHexagonsWhenGetHexagonsByAxialRangeIsCalled() {
        List<GridCell> expected = new ArrayList<>();

        addCoordIfPresent(expected, target, 2, 3);
        addCoordIfPresent(expected, target, 3, 3);
        addCoordIfPresent(expected, target, 4, 3);

        addCoordIfPresent(expected, target, 2, 4);
        addCoordIfPresent(expected, target, 3, 4);
        addCoordIfPresent(expected, target, 4, 4);

        addCoordIfPresent(expected, target, 2, 5);
        addCoordIfPresent(expected, target, 3, 5);
        addCoordIfPresent(expected, target, 4, 5);

        Collection<GridCell> actual = target.getHexagonsByCubeRange(fromCoordinates(GRID_X_FROM, GRID_Z_FROM), fromCoordinates(GRID_X_TO, GRID_Z_TO));

        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldReturnProperHexagonsWhenGetHexagonsByOffsetRangeIsCalled() {
        List<GridCell> expected = new ArrayList<>();

        addCoordIfPresent(expected, target, 1, 3);
        addCoordIfPresent(expected, target, 2, 3);
        addCoordIfPresent(expected, target, 3, 3);

        addCoordIfPresent(expected, target, 0, 4);
        addCoordIfPresent(expected, target, 1, 4);
        addCoordIfPresent(expected, target, 2, 4);

        addCoordIfPresent(expected, target, 0, 5);
        addCoordIfPresent(expected, target, 1, 5);
        addCoordIfPresent(expected, target, 2, 5);

        Collection<GridCell> actual = target.getHexagonsByOffsetRange(GRID_X_FROM, GRID_X_TO, GRID_Z_FROM, GRID_Z_TO);

        assertTrue(contentsEqual(expected, actual));
    }

    @Test
    public void shouldContainCoordinateWhenContainsCoorinateIsCalledWithProperParameters() {
        int gridX = 2;
        int gridZ = 3;
        assertTrue(target.containsCubeCoordinate(fromCoordinates(gridX, gridZ)));
    }

    @Test
    public void shouldReturnHexagonWhenGetByGridCoordinateIsCalledWithProperCoordinates() {
        int gridX = 2;
        int gridZ = 3;
        Optional<GridCell> hex = target.getByCubeCoordinate(fromCoordinates(gridX, gridZ));
        assertTrue(hex.isPresent());
    }

    @Test
    public void shouldBeEmptyWhenGetByGridCoordinateIsCalledWithInvalidCoordinates() {
        int gridX = 20;
        int gridZ = 30;
        Optional<GridCell> hex = target.getByCubeCoordinate(fromCoordinates(gridX, gridZ));
        assertFalse(hex.isPresent());
    }

    @Test
    public void shouldReturnHexagonWhenCalledWithProperCoordinates() {
        double x = 310.0;
        double y = 255.0;
        Optional<GridCell> hex = target.getByPixelCoordinate(Point.fromPosition(x, y));
        assertTrue(hex.isPresent());

        assertEquals(hex.get().getCoordinate().gridX(), 3);
        assertEquals(hex.get().getCoordinate().gridZ(), 5);
    }

    @Test
    public void shouldReturnHexagonWhenCalledWithProperCoordinates2() {
        double x = 300.0;
        double y = 275.0;
        Optional<GridCell> hex = target.getByPixelCoordinate(Point.fromPosition(x, y));
        assertTrue(hex.isPresent());

        assertEquals(hex.get().getCoordinate().gridX(), 3);
        assertEquals(hex.get().getCoordinate().gridZ(), 5);
    }

    @Test
    public void shouldReturnHexagonWhenCalledWithProperCoordinates3() {
        double x = 325.0;
        double y = 275.0;
        Optional<GridCell> hex = target.getByPixelCoordinate(Point.fromPosition(x, y));
        assertTrue(hex.isPresent());

        assertEquals(hex.get().getCoordinate().gridX(), 3);
        assertEquals(hex.get().getCoordinate().gridZ(), 5);
    }

    @Test
    public void shouldReturnProperNeighborsOfHexagonWhenHexIsInMiddle() {
        Optional<GridCell> hex = target.getByCubeCoordinate(fromCoordinates(3, 7));
        assertTrue(hex.isPresent());

        List<GridCell> expected = new ArrayList<>();

        addCoordIfPresent(expected, target, 4, 7);
        addCoordIfPresent(expected, target, 4, 6);
        addCoordIfPresent(expected, target, 3, 6);
        addCoordIfPresent(expected, target, 2, 7);
        addCoordIfPresent(expected, target, 2, 8);
        addCoordIfPresent(expected, target, 3, 8);


        Collection<GridCell> actual = target.getNeighborsOf(hex.get());
        assertIterableEquals(expected, actual);
    }

    @Test
    abstract public void shouldReturnProperNeighborsOfHexagonWhenHexIsOnTheEdge();

    @Test
    abstract public void shouldProperlyReturnGridLayoutWhenGetGridLayoutIsCalled();

    // TODO
    @Test
    public void shouldProperlyReturnSharedHexagonDataWhenGetSharedHexagonDataIsCalled() {
        assertEquals(builder.getGridSpec().getHexagonHeight(), target.getGridSpec().getHexagonHeight());
        assertEquals(builder.getGridSpec().getHexagonWidth(), target.getGridSpec().getHexagonWidth());
        assertEquals(builder.getGridSpec().getInnerRadius(), target.getGridSpec().getInnerRadius());
        assertEquals(builder.getGridSpec().getOrientation(), target.getGridSpec().getOrientation());
    }

    @Test
    public void shouldProperlyReturnGridWidthWhenGetGridWidthIsCalled() {
        assertEquals(GRID_WIDTH, target.getGridSpec().getGridWidth());
    }

    @Test
    public void shouldProperlyReturnGridHeightWhenGetGridHeightIsCalled() {
        assertEquals(GRID_HEIGHT, target.getGridSpec().getGridHeight());
    }

    protected void addCoordIfPresent(
            Collection<GridCell> target,
            HexagonalGrid grid,
            int gridX, int gridY)
    {
        Optional<GridCell> foundCell = grid.getByCubeCoordinate(fromCoordinates(gridX, gridY));
        foundCell.ifPresent(target::add);
    }
}
