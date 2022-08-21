package org.hexworks.mixite2.core.geometry;

import org.junit.jupiter.api.Test;

import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromAxialKey;
import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CubeCoordinateTest 
{
    private final int TEST_GRID_X = 4;
    private final int TEST_GRID_Z = 5;

    private CubeCoordinate target = fromCoordinates(TEST_GRID_X, TEST_GRID_Z);

    @Test
    public void shouldReturnProperCoordinateWhenGetGridXIsCalled() {
        assertEquals(TEST_GRID_X, target.gridX());
    }

    @Test
    public void shouldBeEqualToItself() {
        assertEquals(target, target);
    }

    @Test
    public void shouldReturnProperCoordinateWhenGetGridZIsCalled() {
        assertEquals(TEST_GRID_Z, target.gridZ());
    }

    @Test
    public void shouldReturnProperKeyWhenToKeyIsCalled() {
        assertEquals(TEST_GRID_X + "," + TEST_GRID_Z, target.toAxialKey());
    }

    @Test
    public void shouldCreateProperAxialCoordinateWhenFromKeyIsCalled() {
        CubeCoordinate result = fromAxialKey(TEST_GRID_X + "," + TEST_GRID_Z);
        assertEquals(target.gridX(), result.gridX());
        assertEquals(target.gridZ(), result.gridZ());
    }

    @Test
    public void shouldFailToCreateCoordinateFromMalformedKey() {
        assertThrows(IllegalArgumentException.class,
                () -> CubeCoordinate.fromAxialKey("asdf"));
    }
}
