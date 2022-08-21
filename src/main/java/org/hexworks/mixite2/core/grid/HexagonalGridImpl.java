package org.hexworks.mixite2.core.grid;

import org.hexworks.mixite2.core.geometry.CoordinateConverter;
import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.geometry.Hexagon;
import org.hexworks.mixite2.core.geometry.Point;

import java.util.*;

public class HexagonalGridImpl implements HexagonalGrid
{
    private static final List<int[]> NEIGHBORS = Arrays.asList(
            new int[]{+1, 0},
            new int[]{+1, -1},
            new int[]{0, -1},
            new int[]{-1, 0},
            new int[]{-1, +1},
            new int[]{0, +1}
    );
    private static final int NEIGHBOR_X_INDEX = 0;
    private static final int NEIGHBOR_Z_INDEX = 1;
    private static final CoordinateConverter coordConverter = new CoordinateConverter();

    private final GridSpec gridSpec;

    private final HashSet<GridCell> cells = new HashSet<>();


    public HexagonalGridImpl(GridSpec gridSpec)
    {
        this.gridSpec = gridSpec;

        for (CubeCoordinate cubeCoordinate : this.gridSpec.getGridLayout().fetchGridCoordinates(gridSpec))
        {
            cells.add(new GridCell(cubeCoordinate, new Hexagon(cubeCoordinate, gridSpec)));
        }
    }

    public List<GridCell> getHexagonsByCubeRange(CubeCoordinate from, CubeCoordinate to)
    {
        List<GridCell> coordinates = new ArrayList<>(Math.abs(from.gridZ() - to.gridZ()) + Math.abs(from.gridX() - to.gridX()));

        for (int gridZ = from.gridZ(); gridZ <= to.gridZ(); gridZ++)
        {
            for (int gridX = from.gridX(); gridX <= to.gridX(); gridX++)
            {
                // TODO: Verify that this works and that the names are accurate.
                Optional<GridCell> coord = getByGridCoordinate(gridX, gridZ);
                coord.ifPresent(coordinates::add);
            }
        }

        return coordinates;
    }

    public List<GridCell> getHexagonsByOffsetRange(int gridXFrom, int gridXTo, int gridYFrom, int gridYTo)
    {
        List<GridCell> coordinates = new ArrayList<>();

        for (int gridX = gridXFrom; gridX <= gridXTo; gridX++)
        {
            for (int gridY = gridYFrom; gridY <= gridYTo; gridY++)
            {
                // TODO: Verify that this works and that the names are accurate.
                int cubeX = coordConverter.convertOffsetCoordinatesToCubeX(gridX, gridY, gridSpec.getOrientation());
                int cubeZ = coordConverter.convertOffsetCoordinatesToCubeZ(gridX, gridY, gridSpec.getOrientation());
                Optional<GridCell> coord = getByGridCoordinate(cubeX, cubeZ);
                coord.ifPresent(coordinates::add);
            }
        }

        return coordinates;
    }

    public boolean containsCubeCoordinate(CubeCoordinate coordinate)
    {
        return this.getByCubeCoordinate(coordinate).isPresent();
    }

    public Optional<GridCell> getByCubeCoordinate(CubeCoordinate coordinate)
    {
        // TODO: Verify that this has the desired effect. Is 'cube coordinate' the same as 'grid coordinate'?
        return getByGridCoordinate(coordinate.gridX(), coordinate.gridZ());
    }

    public Optional<GridCell> getByGridCoordinate(int gridX, int gridZ)
    {
        return cells.stream()
                .filter(cell -> cell.equals(gridX, gridZ))
                .findFirst();
    }

    public Optional<GridCell> getByPixelCoordinate(Point pixelCoord)
    {
        int estimatedGridX = (int) (pixelCoord.coordinateX() / gridSpec.getHexagonWidth());
        int estimatedGridZ = (int) (pixelCoord.coordinateY() / gridSpec.getHexagonHeight());
        estimatedGridX = coordConverter.convertOffsetCoordinatesToCubeX(
            estimatedGridX,
            estimatedGridZ,
            gridSpec.getOrientation()
        );
        estimatedGridZ = coordConverter.convertOffsetCoordinatesToCubeZ(
            estimatedGridX,
            estimatedGridZ,
            gridSpec.getOrientation()
        );

        // We might be off-grid, so we can't just choose the cell that ought to exist.
        // Instead, estimate the cubic coordinates of the point (done above), determine
        // the valid neighbors, and find the smallest distance among those. However, if
        // the estimated coordinate is within the grid's inner radius, just use that and
        // don't bother about the neighbors.

        Optional<GridCell> estimatedCell = getByGridCoordinate(estimatedGridX, estimatedGridZ);
        Optional<GridCell> closestCell = Optional.empty();

        // It's unlikely that we will have a null estimate, but check just in case.
        if (estimatedCell.isPresent())
        {

            double estCellDistance = pixelCoord.distanceFrom(estimatedCell.get().getHexagon().getCenter());
            if(estCellDistance <= gridSpec.getInnerRadius())
            {
                closestCell = estimatedCell;
            }
            else
            {
                // Use the distance as the key so that it's easy to look up the cell.
                HashMap<Double, GridCell> distances = new HashMap<>();

                // If we didn't find the estimated cell in our real grid, look for neighbors.
                for (int[] neighborCoords : NEIGHBORS)
                {
                    var neighbor = getByGridCoordinate(
                            neighborCoords[NEIGHBOR_X_INDEX] + estimatedCell.get().getCoordinate().gridX(),
                            neighborCoords[NEIGHBOR_Z_INDEX] + estimatedCell.get().getCoordinate().gridZ()
                    );
                    if (neighbor.isPresent())
                    {
                        distances.put(pixelCoord.distanceFrom(neighbor.get().getHexagon().getCenter()), neighbor.get());
                    }
                }

                if(!distances.isEmpty())
                {
                    Optional<Double> minDist = distances.keySet()
                            .stream()
                            .min(Double::compareTo);
                    closestCell = Optional.of(distances.get(minDist.get()));
                }
            }
        }

        return closestCell;
    }

    private CubeCoordinate _getNeighborByIndex(GridCell hexagon, int index)
    {
        return CubeCoordinate.fromCoordinates(
                hexagon.getCoordinate().gridX() + NEIGHBORS.get(index)[NEIGHBOR_X_INDEX],
                hexagon.getCoordinate().gridZ() + NEIGHBORS.get(index)[NEIGHBOR_Z_INDEX]
        );
    }
    public CubeCoordinate getNeighborCoordinateByIndex(CubeCoordinate coordinate, int index)
    {
        return CubeCoordinate.fromCoordinates(
                coordinate.gridX() + NEIGHBORS.get(index)[NEIGHBOR_X_INDEX],
                coordinate.gridZ() + NEIGHBORS.get(index)[NEIGHBOR_Z_INDEX]
        );
    }
    public Optional<GridCell> getNeighborByIndex(GridCell hexagon, int index)

    {
        return getByCubeCoordinate(_getNeighborByIndex(hexagon, index));
    }

    public Collection<GridCell> getNeighborsOf(GridCell hexagon)
    {
        List<GridCell> neighbors = new ArrayList<>();
        for (int i = 0; i < NEIGHBORS.size(); i++)
        {
            Optional<GridCell> retHex = getNeighborByIndex(hexagon, i);
            retHex.ifPresent(neighbors::add);
        }
        return neighbors;
    }

    @Override
    public GridSpec getGridSpec()
    {
        return gridSpec;
    }

    public Collection<GridCell> getCells()
    {
        return cells;
    }
}
