package org.hexworks.mixite2.core.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    public void shouldProperlyCreatePointWhenConstructorIsCalled() {
        double x = 0.0;
        double y = 1.0;
        Point p = Point.fromPosition(x, y);
        assertEquals(x, p.coordinateX());
        assertEquals(y, p.coordinateY());
    }

    @Test
    public void shouldProperlyCalculateDistanceBetweenTwoPoints() {
        double y2 = 5.0;
        double y1 = 4.0;
        double x2 = 9.0;
        double x1 = 6.0;
        double expectedDistance = Math.sqrt(((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)));
        double actualDistance = Point.fromPosition(x1, y1).distanceFrom(Point.fromPosition(x2, y2));
        assertEquals(expectedDistance, actualDistance);
    }
}
