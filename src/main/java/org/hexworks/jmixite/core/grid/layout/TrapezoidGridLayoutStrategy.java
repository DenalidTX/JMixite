package org.hexworks.jmixite.core.grid.layout;

import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import org.hexworks.jmixite.core.grid.GridSpec;

import java.util.ArrayList;
import java.util.Collection;

class TrapezoidGridLayoutStrategy implements GridLayoutStrategy
{

    public Collection<CubeCoordinate> fetchGridCoordinates(GridSpec gridSpec)
    {
        ArrayList<CubeCoordinate> coords = new ArrayList<>(gridSpec.getGridHeight() * gridSpec.getGridWidth());


        // TODO: Check for off-by-one errors. (Kotlin "until")
        for (int gridZ = 0; gridZ < gridSpec.getGridHeight(); gridZ++)
        {
            for (int gridX = 0; gridX < gridSpec.getGridWidth(); gridX++)
            {
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
        return "TRAPEZOID";
    }
}
