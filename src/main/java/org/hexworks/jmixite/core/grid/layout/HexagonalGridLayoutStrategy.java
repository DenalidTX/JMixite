package org.hexworks.jmixite.core.grid.layout;

import org.hexworks.jmixite.core.geometry.CubeCoordinate;
import org.hexworks.jmixite.core.geometry.HexagonOrientation;
import org.hexworks.jmixite.core.grid.GridSpec;

import java.util.ArrayList;
import java.util.Collection;

class HexagonalGridLayoutStrategy implements GridLayoutStrategy
{

    public Collection<CubeCoordinate> fetchGridCoordinates(GridSpec gridSpec)
    {
        int gridSize = gridSpec.getGridHeight();
        ArrayList<CubeCoordinate> coords = new ArrayList<>(1 + (gridSize * gridSize * 6 - 6) / 8); // TODO cell count owned by the builder
        int startX = (int) ((HexagonOrientation.FLAT_TOP.equals(gridSpec.getOrientation())) ? Math.floor(gridSize / 2.0) : Math.round(gridSize / 4.0));
        int hexRadius = (int) Math.floor(gridSize / 2.0);
        int minX = startX - hexRadius;

        for (int y = 0; y < gridSize; y++)
        {
            int distanceFromMid = Math.abs(hexRadius - y);

            startX = Math.max(startX, minX);
            int endX = startX + hexRadius + hexRadius - distanceFromMid;
            for (int x = startX; x <= endX; x++)
            {
                int gridZ = (HexagonOrientation.FLAT_TOP.equals(gridSpec.getOrientation())) ? (int) (y - Math.floor(gridSize / 4.0)) : y;
                coords.add(CubeCoordinate.fromCoordinates(x, gridZ));
            }
            startX--;
        }
        return coords;
    }

    public boolean checkParameters(int gridHeight, int gridWidth)
    {
        boolean superResult = checkCommonCase(gridHeight, gridWidth);
        boolean result = gridHeight == gridWidth && Math.abs(gridHeight % 2) == 1;
        return result && superResult;
    }

    public String getName()
    {
        return "HEXAGONAL";
    }

}
