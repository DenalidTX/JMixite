package org.hexworks.jmixite.core.grid.builder;

import org.hexworks.jmixite.core.geometry.HexagonOrientation;
import org.hexworks.jmixite.core.grid.GridSpec;
import org.hexworks.jmixite.core.grid.HexagonalGrid;
import org.hexworks.jmixite.core.grid.HexagonalGridImpl;
import org.hexworks.jmixite.core.grid.calculator.HexagonalGridCalculatorImpl;
import org.hexworks.jmixite.core.grid.layout.GridLayoutStrategy;
import org.hexworks.jmixite.core.grid.layout.RectangularGridLayoutStrategy;
import org.hexworks.jmixite.core.grid.calculator.HexagonalGridCalculator;

/**
 *
 * Builder for a [HexagonalGrid].
 * Can be used to build a [HexagonalGrid].
 * Mandatory parameters are:
 *
 *  * width of the grid
 *  * height of the grid
 *  * radius of a [Hexagon]
 *
 * Defaults for orientation and grid layout are POINTY_TOP and RECTANGULAR.
 */
public class HexagonalGridBuilder
{
    private int gridWidth = 0;
    private int gridHeight = 0;
    private double radius = 0d;
    private HexagonOrientation orientation = HexagonOrientation.POINTY_TOP;

    private GridLayoutStrategy gridLayoutStrategy = new RectangularGridLayoutStrategy();

    /**
     * Returns the GridData.
     *
     * @return grid data
     */
    private GridSpec gridSpec;

    public GridSpec getGridSpec()
    {
        if (gridSpec == null)
        {
            if (radius == 0.0 || gridWidth == 0 || gridHeight == 0)
            {
                throw new IllegalStateException("Not all necessary fields are initialized!");
            }
            gridSpec = new GridSpec(orientation, gridLayoutStrategy, radius, gridWidth, gridHeight);
        }
        return gridSpec;
    }

    /**
     * Builds a [HexagonalGrid] using the parameters supplied.
     * Throws [HexagonalGridCreationException] if not all mandatory parameters
     * are filled and/or they are not valid. In both cases you will be supplied with
     * a [HexagonalGridCreationException] detailing the cause of failure.
     *
     * @return [HexagonalGrid]
     */
    public HexagonalGrid build()
    {
        checkParameters();
        return new HexagonalGridImpl(getGridSpec());
    }

    /**
     * Creates a [HexagonalGridCalculator] for your [HexagonalGrid].
     *
     * @param hexagonalGrid grid
     *
     * @return calculator
     */
    public HexagonalGridCalculator buildCalculatorFor(HexagonalGrid hexagonalGrid)
    {
        return new HexagonalGridCalculatorImpl(hexagonalGrid);
    }

    public double getRadius()
    {
        return radius;
    }

    /**
     * Sets the radius of the [Hexagon]s contained in the resulting [HexagonalGrid].
     *
     * @param radius in pixels
     *
     * @return this [HexagonalGridBuilder]
     */
    public HexagonalGridBuilder setRadius(double radius)
    {
        this.radius = radius;
        return this;
    }

    int getGridWidth()
    {
        return gridWidth;
    }

    /**
     * Mandatory parameter. Sets the number of [Hexagon]s in the horizontal direction.
     *
     * @param gridWidth grid width
     *
     * @return this [HexagonalGridBuilder]
     */
    public HexagonalGridBuilder setGridWidth(int gridWidth)
    {
        this.gridWidth = gridWidth;
        return this;
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    /**
     * Mandatory parameter. Sets the number of [Hexagon]s in the vertical direction.
     *
     * @param gridHeight grid height
     *
     * @return this [HexagonalGridBuilder]
     */
    public HexagonalGridBuilder setGridHeight(int gridHeight)
    {
        this.gridHeight = gridHeight;
        return this;
    }

    public HexagonOrientation getOrientation()
    {
        return orientation;
    }

    /**
     * Sets the [HexagonOrientation] used in the resulting [HexagonalGrid].
     * If it is not set HexagonOrientation.POINTY will be used.
     *
     * @param orientation orientation
     * @return this [HexagonalGridBuilder]
     */
    public HexagonalGridBuilder setOrientation(HexagonOrientation orientation)
    {
        this.orientation = orientation;
        return this;
    }

    /**
     * Sets the [GridLayoutStrategy] which will be used when creating the [HexagonalGrid].
     * If it is not set <pre>RECTANGULAR</pre> will be assumed.
     *
     * @param gridLayout layout
     * @return this [HexagonalGridBuilder].
     */
    public HexagonalGridBuilder setGridLayout(GridLayoutStrategy gridLayout)
    {
        this.gridLayoutStrategy = gridLayout;
        return this;
    }

    public GridLayoutStrategy getGridLayoutStrategy()
    {
        return gridLayoutStrategy;
    }

    private void checkParameters()
    {
        if (radius <= 0) {
            throw new IllegalStateException("Radius must be greater than 0.");
        }
        if (!gridLayoutStrategy.checkParameters(gridHeight, gridWidth)) {
            throw new IllegalStateException("Width: " + gridWidth + " and height: " + gridHeight
                    + " is not valid for: " + gridLayoutStrategy.getName() + " layout.");
        }
    }
}
