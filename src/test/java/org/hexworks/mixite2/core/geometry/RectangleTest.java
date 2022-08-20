package org.hexworks.mixite2.core.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @Test
    public void containsPointTest() {
        Rectangle rectangle = new Rectangle(0.0, 0.0, 100.0, 100.0);
        assertTrue(rectangle.contains(50.0, 50.0));
        assertFalse(rectangle.contains(150.0, 110.0));
    }

    @Test
    public void containsRectTest() {
        Rectangle rectangle = new Rectangle(0.0, 0.0, 100.0, 100.0);
        Rectangle rectangle2 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        Rectangle rectangle3 = new Rectangle(11.0, 11.0, 50.0, 50.0);
        assertFalse(rectangle.contains(rectangle));
        assertFalse(rectangle.contains(rectangle2));
        assertTrue(rectangle.contains(rectangle3));
    }

    @Test
    public void overlapsTest() {
        Rectangle rectangle = new Rectangle(30.0, 30.0, 100.0, 100.0);
        Rectangle rectangle2 = new Rectangle(0.0, 0.0, 50.0, 50.0);
        Rectangle rectangle3 = new Rectangle(31.0, 31.0, 50.0, 50.0);
        Rectangle rectangle4 = new Rectangle(0.0, 0.0, 10.0, 10.0);
        assertTrue(rectangle.overlaps(rectangle2));
        assertTrue(rectangle.overlaps(rectangle3));
        assertFalse(rectangle.overlaps(rectangle4));
    }

    @Test
    public void getAspectRatioTest() {
        Rectangle rectangle = new Rectangle(0.0, 0.0, 1920.0, 1080.0);
        assertEquals(rectangle.aspectRatio(), 16.0 / 9.0);
    }
}
