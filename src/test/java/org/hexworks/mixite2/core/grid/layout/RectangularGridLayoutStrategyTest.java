package org.hexworks.mixite2.core.grid.layout;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromCoordinates;
import org.hexworks.mixite2.core.grid.builder.HexagonalGridBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hexworks.mixite2.core.grid.layout.GridLayoutStrategyTestUtil.fetchDefaultBuilder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RectangularGridLayoutStrategyTest 
{

    private final HexagonalGridBuilder builder = fetchDefaultBuilder();
    private final RectangularGridLayoutStrategy target = new RectangularGridLayoutStrategy();

    @Test
    public void shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled() 
    {
        Collection<CubeCoordinate> coords = target.fetchGridCoordinates(builder.getGridSpec());

        assertTrue(coords.contains(fromCoordinates(0, 0)));
        assertTrue(coords.contains(fromCoordinates(1, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 0)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));
        assertTrue(coords.contains(fromCoordinates(1, 1)));
        assertTrue(coords.contains(fromCoordinates(2, 1)));
        assertTrue(coords.contains(fromCoordinates(-1, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 2)));
        assertTrue(coords.contains(fromCoordinates(1, 2)));

        assertFalse(coords.contains(fromCoordinates(-1, 0)));
        assertFalse(coords.contains(fromCoordinates(0, -1)));
        assertFalse(coords.contains(fromCoordinates(1, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -1)));
        assertFalse(coords.contains(fromCoordinates(3, -1)));
        assertFalse(coords.contains(fromCoordinates(3, 0)));
        assertFalse(coords.contains(fromCoordinates(3, 1)));
        assertFalse(coords.contains(fromCoordinates(2, 2)));
        assertFalse(coords.contains(fromCoordinates(1, 3)));
        assertFalse(coords.contains(fromCoordinates(0, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 3)));
        assertFalse(coords.contains(fromCoordinates(-2, 3)));
        assertFalse(coords.contains(fromCoordinates(-2, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }

    @Test
    public void shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() 
    {
        Collection<CubeCoordinate> coords = target.fetchGridCoordinates(builder.getGridSpec());

        assertTrue(coords.contains(fromCoordinates(0, 0)));
        assertTrue(coords.contains(fromCoordinates(1, 0)));
        assertTrue(coords.contains(fromCoordinates(2, -1)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));
        assertTrue(coords.contains(fromCoordinates(1, 1)));
        assertTrue(coords.contains(fromCoordinates(2, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 1)));
        assertTrue(coords.contains(fromCoordinates(0, 2)));
        assertTrue(coords.contains(fromCoordinates(1, 2)));

        assertFalse(coords.contains(fromCoordinates(-1, 0)));
        assertFalse(coords.contains(fromCoordinates(0, -1)));
        assertFalse(coords.contains(fromCoordinates(1, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -2)));
        assertFalse(coords.contains(fromCoordinates(3, -1)));
        assertFalse(coords.contains(fromCoordinates(3, 0)));
        assertFalse(coords.contains(fromCoordinates(3, 1)));
        assertFalse(coords.contains(fromCoordinates(2, 2)));
        assertFalse(coords.contains(fromCoordinates(1, 3)));
        assertFalse(coords.contains(fromCoordinates(0, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 3)));
        assertFalse(coords.contains(fromCoordinates(-2, 3)));
        assertFalse(coords.contains(fromCoordinates(-2, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }

    @Test
    public void testCheckParameters0()
    {
        boolean result = target.checkParameters(1, 1); // super: true, derived: true
        assertTrue(result);
    }

    @Test
    public void testCheckParameters1()
    {
        boolean result = target.checkParameters(0, 0); // super: false, derived: false;
        assertFalse(result);
    }

    @Test
    public void testCheckParameters2()
    {
        boolean result = target.checkParameters(-1, -1); // super: false, derived: true;
        assertFalse(result);
    }

    @Test
    public void testCheckParameters3()
    {
        boolean result = target.checkParameters(1, 2); // super: true, derived: true
        assertTrue(result);
    }

}
