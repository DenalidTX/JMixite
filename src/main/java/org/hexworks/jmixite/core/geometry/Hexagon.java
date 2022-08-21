package org.hexworks.jmixite.core.geometry;

import org.hexworks.jmixite.core.grid.GridSpec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Hexagon.
 * *Please note* that all coordinates are relative to the [HexagonalGrid] containing this [Hexagon].
 */
public class Hexagon
{
    private final CubeCoordinate cubeCoordinate;
    private final GridSpec sharedData;

    /**
     * Returns a list containing the [Point]s of this [Hexagon].
     */
    List<Point> points = new ArrayList<>();

    /**
     * Returns an array of the vertices of this [Hexagon].
     */
    List<Double> vertices = new ArrayList<>();

    /**
     * Returns a rectangle defining the **external** boundary box of this [Hexagon]
     * (a rectangle that hits the 2 pointy corners and the 2 flat sides).
     */
    private Rectangle externalBoundingBox;

    /**
     * Returns a rectangle defining the **internal** boundary box of this [Hexagon]
     * (the biggest rectangle that hits the outline of the [Hexagon] exactly 4 times).
     */
    private Rectangle internalBoundingBox;

    /**
     * Returns the center (pixel) [Point] of this [Hexagon].
     */
    private final Point center;


    public Hexagon(CubeCoordinate cubeCoordinate, GridSpec sharedData)
    {
        this.cubeCoordinate = cubeCoordinate;
        this.sharedData = sharedData;

        center = calculateCenter();

        this.points = calculatePoints();
        double x1 = points.get(3).coordinateX();
        double y1 = points.get(2).coordinateY();
        double x2 = points.get(0).coordinateX();
        double y2 = points.get(5).coordinateY();

        externalBoundingBox = new Rectangle(x1, y1, x2 - x1, y2 - y1);
        internalBoundingBox = new Rectangle((center.coordinateX() - 1.25 * sharedData.getOuterRadius() / 2),
            (center.coordinateY() - 1.25 * sharedData.getOuterRadius() / 2),
            (1.25f * sharedData.getOuterRadius()),
            (1.25f * sharedData.getOuterRadius()));

        this.vertices = new ArrayList(12);
        for (Point point : points) {
            vertices.add(point.coordinateX());
            vertices.add(point.coordinateY());
        }
    }

    private Point calculateCenter()
    {
        if (HexagonOrientation.FLAT_TOP.equals(sharedData.getOrientation()))
        {
            return Point.fromPosition(
                cubeCoordinate.gridX() * sharedData.getHexagonWidth() + sharedData.getOuterRadius(),
                cubeCoordinate.gridZ() * sharedData.getHexagonHeight() + cubeCoordinate.gridX() * sharedData.getHexagonHeight() / 2 + sharedData.getHexagonHeight() / 2
            );
        }
        else
        {
            return Point.fromPosition(
                cubeCoordinate.gridX() * sharedData.getHexagonWidth() + cubeCoordinate.gridZ() * sharedData.getHexagonWidth() / 2 + sharedData.getHexagonWidth() / 2,
                cubeCoordinate.gridZ() * sharedData.getHexagonHeight() + sharedData.getOuterRadius()
            );
        }
    }

    public Point getCenter() {
        return center;
    }

    private List<Point> calculatePoints() {
        ArrayList<Point> points = new ArrayList<>(6);
        for (int i=0; i<6; i++)
        {
            double angle = 2 * Math.PI / 6 * (i + sharedData.getOrientation().coordinateOffset);
            double x = center.coordinateX() + sharedData.getOuterRadius() * Math.cos(angle);
            double y = center.coordinateY() + sharedData.getOuterRadius() * Math.sin(angle);
            points.add(Point.fromPosition(x, y));
        }
        return points;
    }

    public CubeCoordinate cubeCoordinate()
    {
        return cubeCoordinate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || this.getClass() != other.getClass())
            return false;

        return cubeCoordinate == ((Hexagon) other).cubeCoordinate();
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(cubeCoordinate);
    }
}
