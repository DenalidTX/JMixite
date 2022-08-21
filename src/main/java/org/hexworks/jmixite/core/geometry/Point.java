package org.hexworks.jmixite.core.geometry;

/**
 * Represents a point. Please note that this represents a point in
 * 2d space not an abstract concept of a coordinate.
 */
public record Point (double coordinateX, double coordinateY)
{

    /**
     * Calculates a distance between two points.
     *
     * @param point point
     *
     * @return distance
     */
    public double distanceFrom(Point point) {
        return Math.sqrt((this.coordinateX - point.coordinateX) * (this.coordinateX - point.coordinateX)
                + (this.coordinateY - point.coordinateY) * (this.coordinateY - point.coordinateY));
    }


    /**
     * Creates a point from coordinateX and coordinateY positions.
     *
     * @param coordinateX x
     * @param coordinateY y
     *
     * @return point
     */
    public static Point fromPosition(double coordinateX, double coordinateY) {
        return new Point(coordinateX, coordinateY);
    }
}
