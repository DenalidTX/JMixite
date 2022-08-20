package org.hexworks.mixite2.core.grid;

import org.hexworks.mixite2.core.geometry.HexagonOrientation;
import org.hexworks.mixite2.core.grid.layout.GridLayoutStrategy;
/**
 * Immutable class which defines the parameters by which a hexagon grid
 * is defined. This is used both to create the grid and to perform some
 * calculations after instantiation.
 */
public class GridSpec
{
    private final HexagonOrientation orientation;
    private final GridLayoutStrategy gridLayout;
    private final double outerRadius;
    private final int gridWidth;
    private final int gridHeight;
    private final double hexagonHeight;
    private final double hexagonWidth;
    private final double innerRadius;

    public GridSpec(HexagonOrientation orientation, GridLayoutStrategy gridLayout, double outerRadius, int gridWidth, int gridHeight) {
        this.orientation = orientation;
        this.gridLayout = gridLayout;
        this.outerRadius = outerRadius;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        if (this.orientation == HexagonOrientation.FLAT_TOP) {
            // Note: The following comment was in the original mixite library.
            // I don't know if it's right or wrong, so the real fix here is to
            // research the correct math.
            // FIXME These are the wrong way around! flat-top => width > height
            hexagonHeight = calculateHeight(this.outerRadius);
            hexagonWidth = calculateWidth(this.outerRadius);
            innerRadius = hexagonWidth / 2;
        } else {
            hexagonHeight = calculateWidth(this.outerRadius);
            hexagonWidth = calculateHeight(this.outerRadius);
            innerRadius = hexagonHeight / 2;
        }
    }

    private double calculateHeight(double radius)
    {
        return Math.sqrt(3.0) * radius;
    }

    private double calculateWidth(double radius)
    {
        return radius * 3 / 2;
    }

    public double getOuterRadius()
    {
        return outerRadius;
    }

    public double getInnerRadius()
    {
        return innerRadius;
    }

    public int getGridWidth()
    {
        return gridWidth;
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    public HexagonOrientation getOrientation()
    {
        return orientation;
    }

    public GridLayoutStrategy getGridLayout()
    {
        return gridLayout;
    }

    public double getHexagonWidth()
    {
        return hexagonWidth;
    }

    public double getHexagonHeight()
    {
        return hexagonHeight;
    }
}
