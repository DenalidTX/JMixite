package org.hexworks.jmixite.core.geometry;

import org.junit.jupiter.api.Test;

import static org.hexworks.jmixite.core.geometry.CubeCoordinate.fromAxialKey;
import static org.hexworks.jmixite.core.geometry.CubeCoordinate.fromCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CubeCoordinateTest 
{
    private final int TEST_GRID_X = 4;
    private final int TEST_GRID_Z = 5;

    private CubeCoordinate target = fromCoordinates(TEST_GRID_X, TEST_GRID_Z);

    @Test
    public void shouldReturnProperCoordinateWhenGetGridXIsCalled() {
        assertEquals(TEST_GRID_X, target.x());
    }

    @Test
    public void shouldBeEqualToItself() {
        assertEquals(target, target);
    }

    @Test
    public void shouldReturnProperCoordinateWhenGetGridZIsCalled() {
        assertEquals(TEST_GRID_Z, target.z());
    }

    @Test
    public void shouldReturnProperKeyWhenToKeyIsCalled() {
        assertEquals(TEST_GRID_X + "," + TEST_GRID_Z, target.toAxialKey());
    }

    @Test
    public void shouldCreateProperAxialCoordinateWhenFromKeyIsCalled() {
        CubeCoordinate result = fromAxialKey(TEST_GRID_X + "," + TEST_GRID_Z);
        assertEquals(target.x(), result.x());
        assertEquals(target.z(), result.z());
    }

    @Test
    public void shouldFailToCreateCoordinateFromMalformedKey() {
        assertThrows(IllegalArgumentException.class,
                () -> CubeCoordinate.fromAxialKey("asdf"));
    }
}
