package org.hexworks.mixite2.core.grid.calculator;

import org.hexworks.mixite2.core.grid.HexagonalGrid;
import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.geometry.RotationDirection;
import org.hexworks.mixite2.core.grid.GridCell;

import java.util.*;

public class HexagonalGridCalculatorImpl implements HexagonalGridCalculator
{
    private final HexagonalGrid hexagonalGrid;

    public HexagonalGridCalculatorImpl(HexagonalGrid hexagonalGrid)
    {
        this.hexagonalGrid = hexagonalGrid;
    }

    public int calculateDistanceBetween(GridCell first, GridCell second)
    {
        int absX = Math.abs(first.getCoordinate().gridX() - second.getCoordinate().gridX());
        int absY = Math.abs(first.getCoordinate().gridY() - second.getCoordinate().gridY());
        int absZ = Math.abs(first.getCoordinate().gridZ() - second.getCoordinate().gridZ());
        return Math.max(Math.max(absX, absY), absZ);
    }

    public Collection<GridCell> calculateMovementRangeFrom(GridCell from, int distance)
    {
        List<GridCell> ret = new ArrayList<>();
        for (int x = -distance; x <= distance; x++)
        {
            for (int y = Math.max(-distance, -x - distance); y <= Math.min(distance, -x + distance); y++)
            {
                int z = -x - y;
                CubeCoordinate tempCoordinate = CubeCoordinate.fromCoordinates(
                        from.getCoordinate().gridX() + x,
                        from.getCoordinate().gridZ() + z);
                Optional<GridCell> tempCell = hexagonalGrid.getByCubeCoordinate(tempCoordinate);
                tempCell.ifPresent(ret::add);
            }
        }
        return ret;
    }

    public Optional<GridCell> rotateHexagon(
            GridCell centerCell,
            GridCell targetCell,
            RotationDirection rotationDirection)
    {
        int diffX = targetCell.getCoordinate().gridX() - centerCell.getCoordinate().gridX();
        int diffZ = targetCell.getCoordinate().gridZ() - centerCell.getCoordinate().gridZ();
        CubeCoordinate diffCoord = CubeCoordinate.fromCoordinates(diffX, diffZ);
        CubeCoordinate rotatedCoord = rotationDirection.calculateRotation(diffCoord);
        CubeCoordinate resultCoord = CubeCoordinate.fromCoordinates(
            centerCell.getCoordinate().gridX() + rotatedCoord.gridX(),
            centerCell.getCoordinate().gridZ() + rotatedCoord.gridZ()
        ); // 0, x,
        return hexagonalGrid.getByCubeCoordinate(resultCoord);
    }

    public Collection<GridCell> calculateRingFrom(CubeCoordinate centerCoordinate, int radius)
    {
        HashSet<GridCell> result = new HashSet<>();

        var currentCoordinate = CubeCoordinate.fromCoordinates(
            centerCoordinate.gridX() - radius,
            centerCoordinate.gridZ() + radius
        );

        for( int i=0; i<6; i++)
        {
            for(int j=0; j<radius; j++)
            {
                currentCoordinate = hexagonalGrid.getNeighborCoordinateByIndex(currentCoordinate, i);
                Optional<GridCell> currentCell = hexagonalGrid.getByCubeCoordinate(currentCoordinate);
                currentCell.ifPresent(result::add);
            }
        }

        return result;
    }

    public List<GridCell> drawLine(GridCell from, GridCell to)
    {
        int distance = calculateDistanceBetween(from, to);
        if (distance == 0) {
            return Collections.emptyList();
        }
        ArrayList<GridCell> results = new ArrayList<>(distance + 1);
        for (int i=0; i<=distance; i++)
        {
            CubeCoordinate interpolatedCoordinate = cubeLinearInterpolate(
                from.getCoordinate(),
                to.getCoordinate(), 1.0 / distance * i
            );
            Optional<GridCell> currentCell = hexagonalGrid.getByCubeCoordinate(interpolatedCoordinate);
            currentCell.ifPresent(results::add);
        }
        return results;
    }

    private CubeCoordinate cubeLinearInterpolate(CubeCoordinate from, CubeCoordinate to, double sample)
    {
        return roundToCubeCoordinate(
            linearInterpolate(from.gridX(), to.gridX(), sample),
            linearInterpolate(from.gridY(), to.gridY(), sample),
            linearInterpolate(from.gridZ(), to.gridZ(), sample)
        );
    }

    private double linearInterpolate(int from, int to, double sample)
    {
        return from + (to - from) * sample;
    }

    private CubeCoordinate roundToCubeCoordinate(double gridX, double gridY, double gridZ)
    {
        double rx = (int) Math.round(gridX);
        double ry = (int) Math.round(gridY);
        double rz = (int) Math.round(gridZ);

        double differenceX = Math.abs(rx - gridX);
        double differenceY = Math.abs(ry - gridY);
        double differenceZ = Math.abs(rz - gridZ);

        if (differenceX > differenceY && differenceX > differenceZ)
        {
            rx = -ry - rz;
        }
        else if (differenceY <= differenceZ)
        {
            rz = -rx - ry;
        }
        return CubeCoordinate.fromCoordinates((int) rx, (int) rz);
    }
}
