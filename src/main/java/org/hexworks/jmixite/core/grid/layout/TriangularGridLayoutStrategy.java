package org.hexworks.jmixite.core.grid.layout;

import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import org.hexworks.jmixite.core.grid.GridSpec;

import java.util.ArrayList;
import java.util.Collection;

class TriangularGridLayoutStrategy implements GridLayoutStrategy
{

    public Collection<CubeCoordinate> fetchGridCoordinates(GridSpec gridSpec)
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
