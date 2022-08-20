package org.hexworks.mixite2.core.grid.layout;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.grid.GridSpec;

import java.util.ArrayList;

class TriangularGridLayoutStrategy implements GridLayoutStrategy
{

    public Iterable<CubeCoordinate> fetchGridCoordinates(GridSpec gridSpec)
    {
        int gridSize = gridSpec.getGridHeight();
        ArrayList<CubeCoordinate> coords = new ArrayList<>(gridSize * (gridSize + 1) / 2);

        // TODO: Check for off-by-one errors. (Kotlin "until")
        for (int gridZ = 0; gridZ < gridSize; gridZ++)
        {
            int endX = gridSize - gridZ;
            for (int gridX = 0; gridX < endX; gridX++)
            {
                coords.add(CubeCoordinate.fromCoordinates(gridX, gridZ));
            }
        }
        return coords;
    }

    public boolean checkParameters(int gridHeight, int gridWidth)
    {
        boolean superResult = checkCommonCase(gridHeight, gridWidth);
        boolean result = gridHeight == gridWidth;
        return superResult && result;
    }

    public String getName()
    {
        return "TRIANGULAR";
    }
}
