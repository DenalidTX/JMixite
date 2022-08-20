package org.hexworks.mixite2.core.grid.layout;

/**
 * This enum represents the possible shapes a [HexagonalGrid] can have.
 * The name [HexagonalGridLayout] might seem inconsistent with the other names
 * in this package but since the name GridLayout is so common (in SWT for example)
 * using this name seemed appropriate.
 */
public enum HexagonalGridLayout
{

    /**
     * A rectangular layout has no special rules.
     */
    RECTANGULAR(new RectangularGridLayoutStrategy()),

    /**
     * The hexagonal layout must have equal width and height and
     * it must be odd.
     */
    HEXAGONAL(new HexagonalGridLayoutStrategy()),

    /**
     * A triangular layout must have equal width and height.
     */
    TRIANGULAR(new TriangularGridLayoutStrategy()),

    /**
     * A trapezoid layout has no special rules.
     */
    TRAPEZOID(new TrapezoidGridLayoutStrategy());

    private final GridLayoutStrategy gridLayoutStrategy;

    HexagonalGridLayout(GridLayoutStrategy gridLayoutStrategy)
    {
        this.gridLayoutStrategy = gridLayoutStrategy;
    }

    /**
     * Checks whether the grid height/width parameters can be used for the given [GridLayoutStrategy].
     *
     * @return valid?
     */
    private boolean checkParameters(int gridHeight, int gridWidth)
    {
        return gridLayoutStrategy.checkParameters(gridHeight, gridWidth);
    }

    public GridLayoutStrategy getGridLayoutStrategy()
    {
        return gridLayoutStrategy;
    }
}
