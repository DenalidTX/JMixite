package org.hexworks.mixite2.core.grid.layout;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromCoordinates;
import org.hexworks.mixite2.core.geometry.HexagonOrientation;
import org.hexworks.mixite2.core.grid.builder.HexagonalGridBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hexworks.mixite2.core.grid.layout.GridLayoutStrategyTestUtil.fetchDefaultBuilder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TriangularGridLayoutStrategyTest {

    private final HexagonalGridBuilder builder = fetchDefaultBuilder();
    private final TriangularGridLayoutStrategy target = new TriangularGridLayoutStrategy();

    @Test
    public void shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled() {
        testCoordinates(target.fetchGridCoordinates(builder.getGridSpec()));
    }

    @Test
    public void shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() {
        builder.setOrientation(HexagonOrientation.FLAT_TOP);
        testCoordinates(target.fetchGridCoordinates(builder.getGridSpec()));
    }

    @Test
    public void shouldReturnTrueWhenCheckParametersWithOneAndOne() {
        boolean result = target.checkParameters(1, 1); // super: true, derived: true
        assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenCheckParametersWithOneAndTwo() {
        boolean result = target.checkParameters(1, 2); // super: true, derived: false
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenCheckParametersWithZeroAndZero() {
        boolean result = target.checkParameters(0, 0); // super: false, derived: false;
        assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenCheckParametersWithMinusOneAndMinusOne() {
        boolean result = target.checkParameters(-1, -1); // super: false, derived: true;
        assertFalse(result);
    }

    private void testCoordinates(Collection<CubeCoordinate> coords)
    {
        assertTrue(coords.contains(fromCoordinates(0, 0)));
        assertTrue(coords.contains(fromCoordinates(1, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 0)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));
        assertTrue(coords.contains(fromCoordinates(1, 1)));
        assertTrue(coords.contains(fromCoordinates(0, 2)));

        assertFalse(coords.contains(fromCoordinates(-1, 0)));
        assertFalse(coords.contains(fromCoordinates(0, -1)));
        assertFalse(coords.contains(fromCoordinates(1, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -1)));
        assertFalse(coords.contains(fromCoordinates(3, -1)));
        assertFalse(coords.contains(fromCoordinates(3, 0)));
        assertFalse(coords.contains(fromCoordinates(2, 1)));
        assertFalse(coords.contains(fromCoordinates(1, 2)));
        assertFalse(coords.contains(fromCoordinates(0, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }
}

