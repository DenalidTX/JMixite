package org.hexworks.mixite2.core.grid.layout;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.grid.GridSpec;

import java.util.ArrayList;

class TrapezoidGridLayoutStrategy implements GridLayoutStrategy
{

    public Iterable<CubeCoordinate> fetchGridCoordinates(GridSpec gridSpec)
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
