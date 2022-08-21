package org.hexworks.jmixite.core.grid;

import org.hexworks.jmixite.core.TestUtils;
import org.hexworks.jmixite.core.grid.layout.RectangularGridLayoutStrategy;
import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import org.hexworks.jmixite.core.grid.builder.HexagonalGridBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RectangularGridLayoutHexagonImplTest extends HexagonalGridImplTest
{
    public HexagonalGridBuilder getBuilder()
    {
        return new HexagonalGridBuilder()
                .setGridHeight(GRID_HEIGHT)
                .setGridWidth(GRID_WIDTH)
                .setRadius(RADIUS)
                .setOrientation(ORIENTATION);
    }

    @Test
    public void shouldReturnProperHexagonsWhenEachHexagonIsTestedInAGivenGrid() {
        Collection<GridCell> cells = target.getCells();
        HashSet<String> expectedCoordinates = new HashSet<>();

        for(int x=0; x< GRID_WIDTH; x++)
        {
            for(int y=0; y<GRID_HEIGHT; y++)
            {
                int gridX = coordConverter.convertOffsetCoordinatesToCubeX(x, y, ORIENTATION);
                int gridZ = coordConverter.convertOffsetCoordinatesToCubeZ(x, y, ORIENTATION);
                expectedCoordinates.add(gridX + "," + gridZ);
            }
        }

        List<String> actual = cells.stream()
                .map(cell -> cell.getCoordinate().toAxialKey())
                .toList();

        Assertions.assertTrue(TestUtils.contentsEqual(expectedCoordinates, actual));
    }

    @Test
    public void shouldReturnProperNeighborsOfHexagonWhenHexIsOnTheEdge() {
        Optional<GridCell> gridCell = target.getByCubeCoordinate(CubeCoordinate.fromCoordinates(5, 9));
        assertTrue(gridCell.isPresent());

        HashSet<GridCell> expected = new HashSet<>();
        addCoordIfPresent(expected, target, 5, 8);
        addCoordIfPresent(expected, target, 4, 9);

        Collection<GridCell> actual = target.getNeighborsOf(gridCell.get());
        assertIterableEquals(expected, actual);
    }

    @Test
    public void shouldProperlyReturnGridLayoutWhenGetGridLayoutIsCalled()
    {
        assertInstanceOf(RectangularGridLayoutStrategy.class, target.getGridSpec().getGridLayout());
    }
}