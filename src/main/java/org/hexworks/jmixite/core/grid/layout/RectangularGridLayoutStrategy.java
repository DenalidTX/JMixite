package org.hexworks.jmixite.core.grid.layout;

import org.hexworks.jmixite.core.geometry.CoordinateConverter;
import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import org.hexworks.jmixite.core.grid.GridSpec;

import java.util.ArrayList;
import java.util.Collection;

public class RectangularGridLayoutStrategy implements GridLayoutStrategy
{
    private static final CoordinateConverter coordConverter = new CoordinateConverter();

    public Collection<CubeCoordinate> fetchGridCoordinates(GridSpec gridSpec)
    {
        ArrayList<CubeCoordinate> coords = new ArrayList<>( gridSpec.getGridHeight() * gridSpec.getGridWidth());

        for (int offsetY = 0; offsetY < gridSpec.getGridHeight(); offsetY++)
        {
            for (int offsetX = 0; offsetX < gridSpec.getGridWidth(); offsetX++)
            {
                int gridX = coordConverter.convertOffsetCoordinatesToCubeX(offsetX, offsetY, gridSpec.getOrientation());
                int gridZ = coordConverter.convertOffsetCoordinatesToCubeZ(offsetX, offsetY, gridSpec.getOrientation());
                coords.add(CubeCoordinate.fromCoordinates(gridX, gridZ));
            }
        }
        return coords;
    }

    public boolean checkParameters(int gridHeight, int gridWidth)
    {
        return checkCommonCase(gridHeight, gridWidth);
    }

    public String getName()
    {
        return "RECTANGULAR";
    }
}
