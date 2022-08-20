package org.hexworks.mixite2.core.grid.calculator;

import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.geometry.Hexagon;
import org.hexworks.mixite2.core.geometry.RotationDirection;
import org.hexworks.mixite2.core.grid.GridCell;
import org.hexworks.mixite2.core.grid.HexagonalGrid;
import org.hexworks.mixite2.core.grid.builder.HexagonalGridBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

class HexagonalGridCalculatorImplTest {

    private final HexagonalGrid grid;
    private final HexagonalGridCalculator calculator;


    public HexagonalGridCalculatorImplTest()
    {
        HexagonalGridBuilder builder = new HexagonalGridBuilder()
            .setGridHeight(10)
            .setGridWidth(10)
            .setRadius(10.0);
        grid = builder.build();
        calculator = builder.buildCalculatorFor(grid);
    }

    @Test
    public void shouldProperlyCalculateDistanceBetweenTwoHexes() {
        Optional<GridCell> hex0 = grid.getByCubeCoordinate(fromCoordinates(1, 1));
        Optional<GridCell> hex1 = grid.getByCubeCoordinate(fromCoordinates(4, 5));
        assertTrue(hex0.isPresent());
        assertTrue(hex1.isPresent());
        assertEquals(7, calculator.calculateDistanceBetween(hex0.get(), hex1.get()));
    }

    @Test
    public void shouldProperlyCalculateMovementRangeFromHexWith1() {
        Optional<GridCell> hex = grid.getByCubeCoordinate(fromCoordinates(3, 7));
        assertTrue(hex.isPresent());

        HashSet<CubeCoordinate> expected = new HashSet<>();
        expected.add(hex.get().getCoordinate());

        addCoordIfPresent(expected, grid, 3, 6);
        addCoordIfPresent(expected, grid, 4, 6);
        addCoordIfPresent(expected, grid, 4, 7);
        addCoordIfPresent(expected, grid, 3, 8);
        addCoordIfPresent(expected, grid, 2, 8);
        addCoordIfPresent(expected, grid, 2, 7);


        Collection<GridCell> actualCells = calculator.calculateMovementRangeFrom(hex.get(), 1);
        assertIterableEquals(expected,
                actualCells.stream().map(GridCell::getCoordinate).collect(Collectors.toList()));
    }
    

    @Test
    public void shouldProperlyCalculateMovementRangeFromHexWith2() {
        Optional<GridCell> hex = grid.getByCubeCoordinate(fromCoordinates(3, 7));
        assertTrue(hex.isPresent());

        HashSet<CubeCoordinate> expected = new HashSet<>();
        expected.add(hex.get().getCoordinate());

        addCoordIfPresent(expected, grid, 3, 6);
        addCoordIfPresent(expected, grid, 4, 6);
        addCoordIfPresent(expected, grid, 4, 7);
        addCoordIfPresent(expected, grid, 3, 8);
        addCoordIfPresent(expected, grid, 2, 8);
        addCoordIfPresent(expected, grid, 2, 7);
        addCoordIfPresent(expected, grid, 3, 5);
        addCoordIfPresent(expected, grid, 4, 5);
        addCoordIfPresent(expected, grid, 5, 5);
        addCoordIfPresent(expected, grid, 2, 6);
        addCoordIfPresent(expected, grid, 5, 6);
        addCoordIfPresent(expected, grid, 1, 7);
        addCoordIfPresent(expected, grid, 5, 7);
        addCoordIfPresent(expected, grid, 1, 8);
        addCoordIfPresent(expected, grid, 4, 8);
        addCoordIfPresent(expected, grid, 1, 9);
        addCoordIfPresent(expected, grid, 3, 9);
        addCoordIfPresent(expected, grid, 2, 9);


        Collection<GridCell> actualCells = calculator.calculateMovementRangeFrom(hex.get(), 1);
        assertIterableEquals(expected,
                actualCells.stream().map(GridCell::getCoordinate).collect(Collectors.toList()));
    }

    @Test
    public void shouldProperlyCalculateLineWithMultipleElements() {
        Optional<GridCell> fromCell = grid.getByCubeCoordinate(fromCoordinates(3, 7));
        assertTrue(fromCell.isPresent());
        Optional<GridCell> toCell = grid.getByCubeCoordinate(fromCoordinates(8, 1));
        assertTrue(toCell.isPresent());

        HashSet<CubeCoordinate> expected = new HashSet<>();
        expected.add(fromCell.get().getCoordinate());

        addCoordIfPresent(expected, grid, 4, 6);
        addCoordIfPresent(expected, grid, 5, 5);
        addCoordIfPresent(expected, grid, 6, 4);
        addCoordIfPresent(expected, grid, 6, 3);
        addCoordIfPresent(expected, grid, 7, 2);
        addCoordIfPresent(expected, grid, 8, 1);

        expected.add(toCell.get().getCoordinate());
        
        Collection<GridCell> actual = calculator.drawLine(fromCell.get(), toCell.get());
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldProperlyCalculateLineWithOneElement() {
        Optional<GridCell> fromCell = grid.getByCubeCoordinate(fromCoordinates(3, 7));
        assertTrue(fromCell.isPresent());
        Optional<GridCell> toCell = grid.getByCubeCoordinate(fromCoordinates(3, 7));
        assertTrue(toCell.isPresent());
        Collection<GridCell> actual = calculator.drawLine(fromCell.get(), toCell.get());
        assertTrue(actual.isEmpty());
    }

    @Test
    public void shouldProperlyCalculateRotationRightWhenGivenAValidGrid() {
        CubeCoordinate originalCoord = fromCoordinates(3, -1);
        CubeCoordinate targetCoord = fromCoordinates(5, -1);

        GridCell originalCell = new GridCell(originalCoord, new Hexagon(originalCoord, grid.getGridSpec()));
        GridCell targetCell = new GridCell(targetCoord, new Hexagon(targetCoord, grid.getGridSpec()));

        Optional<GridCell> resultCell = calculator.rotateHexagon(originalCell, targetCell, RotationDirection.RIGHT);
        assertTrue(resultCell.isPresent());

        assertEquals(3, resultCell.get().getCoordinate().gridX());
        assertEquals(-4, resultCell.get().getCoordinate().gridY());
        assertEquals(1, resultCell.get().getCoordinate().gridZ());
    }

    @Test
    public void shouldProperlyCalculateRotationLeftWhenGivenAValidGrid() {
        CubeCoordinate originalCoord = fromCoordinates(5, -1);
        CubeCoordinate targetCoord = fromCoordinates(3, -1);

        GridCell originalCell = new GridCell(originalCoord, new Hexagon(originalCoord, grid.getGridSpec()));
        GridCell targetCell = new GridCell(targetCoord, new Hexagon(targetCoord, grid.getGridSpec()));

        Optional<GridCell> resultCell = calculator.rotateHexagon(originalCell, targetCell, RotationDirection.LEFT);
        assertTrue(resultCell.isPresent());

        assertEquals(3, resultCell.get().getCoordinate().gridX());
        assertEquals(-4, resultCell.get().getCoordinate().gridY());
        assertEquals(1, resultCell.get().getCoordinate().gridZ());
    }

    @Test
    public void shouldProperlyCalculateRingWhenGivenValidParameters()
    {
        CubeCoordinate targetCoords = fromCoordinates(9, 0);
        HashSet<CubeCoordinate> expected = new HashSet<>();

        addCoordIfPresent(expected, grid, 2, 7);
        addCoordIfPresent(expected, grid, 2, 7);
        addCoordIfPresent(expected, grid, 3, 7);

        addCoordIfPresent(expected, grid, 5, 6);
        addCoordIfPresent(expected, grid, 6, 5);
        addCoordIfPresent(expected, grid, 7, 4);

        addCoordIfPresent(expected, grid, 7, 3);
        addCoordIfPresent(expected, grid, 7, 2);
        addCoordIfPresent(expected, grid, 7, 1);

        addCoordIfPresent(expected, grid, 6, 1);
        addCoordIfPresent(expected, grid, 5, 1);
        addCoordIfPresent(expected, grid, 4, 1);

        addCoordIfPresent(expected, grid, 3, 2);
        addCoordIfPresent(expected, grid, 2, 3);
        addCoordIfPresent(expected, grid, 1, 4);

        addCoordIfPresent(expected, grid, 1, 5);
        addCoordIfPresent(expected, grid, 1, 6);
        addCoordIfPresent(expected, grid, 1, 7);

        Collection<GridCell> actual = calculator.calculateRingFrom(targetCoords, 3);
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldProperlyCalculateRingWhenNearAnEdge()
    {
        // Note: The braces here are for scoping. The tests here
        // mirror the original Mixite tests, but probably should
        // just be separated.
        {
            CubeCoordinate targetCoords = fromCoordinates(9, 0);
            HashSet<CubeCoordinate> expected = new HashSet<>();

            addCoordIfPresent(expected, grid, 8, 0);
            addCoordIfPresent(expected, grid, 8, 1);
            addCoordIfPresent(expected, grid, 9, 1);

            Collection<GridCell> actual = calculator.calculateRingFrom(targetCoords, 1);
            assertIterableEquals(expected, actual);
        }

        {
            CubeCoordinate targetCoords = fromCoordinates(0, 0);
            HashSet<CubeCoordinate> expected = new HashSet<>();

            addCoordIfPresent(expected, grid, 1, 0);
            addCoordIfPresent(expected, grid, 0, 1);

            Collection<GridCell> actual = calculator.calculateRingFrom(targetCoords, 1);
            assertIterableEquals(expected, actual);
        }
    }

    @Test
    public void shouldProperlyCalculateRingWhenCenterOffEdge() {
        CubeCoordinate targetCoords = fromCoordinates(0, -1);

        HashSet<CubeCoordinate> expected = new HashSet<>();

        addCoordIfPresent(expected, grid, 2, 0);
        addCoordIfPresent(expected, grid, 1, 1);
        addCoordIfPresent(expected, grid, 0, 2);
        addCoordIfPresent(expected, grid, -1, 2);

        Collection<GridCell> actual = calculator.calculateRingFrom(targetCoords, 3);
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldProperlyCalculateRingAtRadiusOne() {
        CubeCoordinate targetCoord = fromCoordinates(4, 4);
        int expected = 6;

        int actual = calculator.calculateRingFrom(targetCoord, 1).size();
        assertEquals(expected, actual);
    }

    private void addCoordIfPresent(Collection<CubeCoordinate> target,
                                   HexagonalGrid grid,
                                   int gridX, int gridY)
    {
        Optional<GridCell> foundCell = grid.getByCubeCoordinate(fromCoordinates(gridX, gridY));
        foundCell.ifPresent(gridCell -> target.add(gridCell.getCoordinate()));
    }
}
