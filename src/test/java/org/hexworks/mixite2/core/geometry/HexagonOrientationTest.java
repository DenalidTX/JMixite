package org.hexworks.mixite2.core.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HexagonOrientationTest {

    @Test
    public void shouldProperlyCalculateFlatCoordinateOffset() {
        assertEquals(0.0f, HexagonOrientation.FLAT_TOP.coordinateOffset);
    }

    @Test
    public void shouldProperlyCalculatePointyCoordinateOffset() {
        assertEquals(0.5f, HexagonOrientation.POINTY_TOP.coordinateOffset);
    }
}
