package org.hexworks.jmixite.core.grid.layout;

import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import static org.hexworks.jmixite.core.geometry.CubeCoordinate.fromCoordinates;
import org.hexworks.jmixite.core.geometry.HexagonOrientation;
import org.hexworks.jmixite.core.grid.builder.HexagonalGridBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.hexworks.jmixite.core.grid.layout.GridLayoutStrategyTestUtil.fetchDefaultBuilder;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HexagonalGridLayoutStrategyTest 
{

    private HexagonalGridBuilder builder = fetchDefaultBuilder();
    private HexagonalGridLayoutStrategy target = new HexagonalGridLayoutStrategy();


    @Test
    public void shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled()
    {
        builder.setOrientation(HexagonOrientation.POINTY_TOP);
        Collection<CubeCoordinate> coords = target.fetchGridCoordinates(builder.getGridSpec());

        assertTrue(coords.contains(fromCoordinates(1, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 1)));
        assertTrue(coords.contains(fromCoordinates(1, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));

        assertFalse(coords.contains(fromCoordinates(0, 0)));
        assertFalse(coords.contains(fromCoordinates(1, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -1)));
        assertFalse(coords.contains(fromCoordinates(3, -1)));
        assertFalse(coords.contains(fromCoordinates(3, 0)));
        assertFalse(coords.contains(fromCoordinates(3, 1)));
        assertFalse(coords.contains(fromCoordinates(2, 2)));
        assertFalse(coords.contains(fromCoordinates(1, 3)));
        assertFalse(coords.contains(fromCoordinates(0, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }

    @Test
    public void shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalledWithBiggerSize() {
        builder.setGridHeight(5)
                .setGridWidth(5)
                .setOrientation(HexagonOrientation.POINTY_TOP);
        Collection<CubeCoordinate> coords = target.fetchGridCoordinates(builder.getGridSpec());

        assertTrue(coords.contains(fromCoordinates(1, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 0)));
        assertTrue(coords.contains(fromCoordinates(3, 0)));
        assertTrue(coords.contains(fromCoordinates(3, 1)));
        assertTrue(coords.contains(fromCoordinates(3, 2)));
        assertTrue(coords.contains(fromCoordinates(2, 3)));
        assertTrue(coords.contains(fromCoordinates(1, 4)));
        assertTrue(coords.contains(fromCoordinates(0, 4)));
        assertTrue(coords.contains(fromCoordinates(-1, 4)));
        assertTrue(coords.contains(fromCoordinates(-1, 3)));
        assertTrue(coords.contains(fromCoordinates(-1, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));

        assertFalse(coords.contains(fromCoordinates(0, 0)));
        assertFalse(coords.contains(fromCoordinates(1, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -1)));
        assertFalse(coords.contains(fromCoordinates(3, -1)));
        assertFalse(coords.contains(fromCoordinates(4, -1)));
        assertFalse(coords.contains(fromCoordinates(4, 0)));
        assertFalse(coords.contains(fromCoordinates(4, 1)));
        assertFalse(coords.contains(fromCoordinates(4, 2)));
        assertFalse(coords.contains(fromCoordinates(3, 3)));
        assertFalse(coords.contains(fromCoordinates(2, 4)));
        assertFalse(coords.contains(fromCoordinates(1, 5)));
        assertFalse(coords.contains(fromCoordinates(0, 5)));
        assertFalse(coords.contains(fromCoordinates(-1, 5)));
        assertFalse(coords.contains(fromCoordinates(-2, 5)));
        assertFalse(coords.contains(fromCoordinates(-2, 4)));
        assertFalse(coords.contains(fromCoordinates(-2, 3)));
        assertFalse(coords.contains(fromCoordinates(-2, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }

    @Test
    public void shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() {
        builder.setOrientation(HexagonOrientation.FLAT_TOP);
        Collection<CubeCoordinate> coords = target.fetchGridCoordinates(builder.getGridSpec());

        assertTrue(coords.contains(fromCoordinates(1, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 1)));
        assertTrue(coords.contains(fromCoordinates(1, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));

        assertFalse(coords.contains(fromCoordinates(0, 0)));
        assertFalse(coords.contains(fromCoordinates(0, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -1)));
        assertFalse(coords.contains(fromCoordinates(3, -1)));
        assertFalse(coords.contains(fromCoordinates(3, 0)));
        assertFalse(coords.contains(fromCoordinates(3, 1)));
        assertFalse(coords.contains(fromCoordinates(2, 2)));
        assertFalse(coords.contains(fromCoordinates(1, 3)));
        assertFalse(coords.contains(fromCoordinates(0, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }

    @Test
    public void shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalledWithBiggerSize() {
        builder.setGridHeight(5).setGridWidth(5)
                .setOrientation(HexagonOrientation.FLAT_TOP);
        Collection<CubeCoordinate> coords = target.fetchGridCoordinates(builder.getGridSpec());

        assertTrue(coords.contains(fromCoordinates(2, -1)));
        assertTrue(coords.contains(fromCoordinates(3, -1)));
        assertTrue(coords.contains(fromCoordinates(4, -1)));
        assertTrue(coords.contains(fromCoordinates(4, 0)));
        assertTrue(coords.contains(fromCoordinates(4, 1)));
        assertTrue(coords.contains(fromCoordinates(3, 2)));
        assertTrue(coords.contains(fromCoordinates(2, 3)));
        assertTrue(coords.contains(fromCoordinates(1, 3)));
        assertTrue(coords.contains(fromCoordinates(0, 3)));
        assertTrue(coords.contains(fromCoordinates(0, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));
        assertTrue(coords.contains(fromCoordinates(1, 0)));

        assertFalse(coords.contains(fromCoordinates(0, 0)));
        assertFalse(coords.contains(fromCoordinates(1, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -2)));
        assertFalse(coords.contains(fromCoordinates(3, -2)));
        assertFalse(coords.contains(fromCoordinates(4, -2)));
        assertFalse(coords.contains(fromCoordinates(5, -2)));
        assertFalse(coords.contains(fromCoordinates(5, -1)));
        assertFalse(coords.contains(fromCoordinates(5, 0)));
        assertFalse(coords.contains(fromCoordinates(5, 1)));
        assertFalse(coords.contains(fromCoordinates(4, 2)));
        assertFalse(coords.contains(fromCoordinates(3, 3)));
        assertFalse(coords.contains(fromCoordinates(2, 4)));
        assertFalse(coords.contains(fromCoordinates(1, 4)));
        assertFalse(coords.contains(fromCoordinates(0, 4)));
        assertFalse(coords.contains(fromCoordinates(-1, 4)));
        assertFalse(coords.contains(fromCoordinates(-1, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }

    @Test
    public void testCheckParameters0() {
        boolean result = target.checkParameters(1, 1); // super: true, derived: true
        assertTrue(result);
    }

    @Test
    public void testCheckParameters1() {
        boolean result = target.checkParameters(1, 2); // super: true, derived: false
        assertFalse(result);
    }

    @Test
    public void testCheckParameters2() {
        boolean result = target.checkParameters(2, 2); // super: true, derived: false
        assertFalse(result);
    }

    @Test
    public void testCheckParameters3() {
        boolean result = target.checkParameters(0, 0); // super: false, derived: false;
        assertFalse(result);
    }

    @Test
    public void testCheckParameters4() {
        boolean result = target.checkParameters(-1, -1); // super: false, derived: true;
        assertFalse(result);
    }

}
