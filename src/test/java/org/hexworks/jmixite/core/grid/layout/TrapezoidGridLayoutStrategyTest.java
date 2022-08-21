package org.hexworks.jmixite.core.grid.layout;

import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import static org.hexworks.jmixite.core.geometry.CubeCoordinate.fromCoordinates;
import org.hexworks.jmixite.core.geometry.HexagonOrientation;
import org.hexworks.jmixite.core.grid.builder.HexagonalGridBuilder;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrapezoidGridLayoutStrategyTest {

    private HexagonalGridBuilder builder = GridLayoutStrategyTestUtil.fetchDefaultBuilder();
    private TrapezoidGridLayoutStrategy target = new TrapezoidGridLayoutStrategy();

    @Test
    public void shouldProperlyCreateHexagonsWithPointyOrientationWhenCreateHexagonsIsCalled() {
        testCoordinates(target.fetchGridCoordinates(builder.getGridSpec()));
    }

    private void testCoordinates(Collection<CubeCoordinate> coords)
    {

        assertTrue(coords.contains(fromCoordinates(0, 0)));
        assertTrue(coords.contains(fromCoordinates(1, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 0)));
        assertTrue(coords.contains(fromCoordinates(2, 1)));
        assertTrue(coords.contains(fromCoordinates(2, 2)));
        assertTrue(coords.contains(fromCoordinates(1, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 2)));
        assertTrue(coords.contains(fromCoordinates(0, 1)));

        assertFalse(coords.contains(fromCoordinates(-1, 0)));
        assertFalse(coords.contains(fromCoordinates(0, -1)));
        assertFalse(coords.contains(fromCoordinates(1, -1)));
        assertFalse(coords.contains(fromCoordinates(2, -1)));
        assertFalse(coords.contains(fromCoordinates(3, -1)));
        assertFalse(coords.contains(fromCoordinates(3, 0)));
        assertFalse(coords.contains(fromCoordinates(3, 1)));
        assertFalse(coords.contains(fromCoordinates(3, 2)));
        assertFalse(coords.contains(fromCoordinates(2, 3)));
        assertFalse(coords.contains(fromCoordinates(1, 3)));
        assertFalse(coords.contains(fromCoordinates(0, 3)));
        assertFalse(coords.contains(fromCoordinates(-1, 2)));
        assertFalse(coords.contains(fromCoordinates(-1, 1)));
    }

    @Test
    public void shouldProperlyCreateHexagonsWithFlatOrientationWhenCreateHexagonsIsCalled() {
        builder.setOrientation(HexagonOrientation.FLAT_TOP);
        testCoordinates(target.fetchGridCoordinates(builder.getGridSpec()));
    }

    @Test
    public void shouldReturnTrueWhenCheckParametersIsCalled() {
        assertTrue(target.checkParameters(2, 2));
    }

}
