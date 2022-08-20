package org.hexworks.mixite2.core.grid;

import org.hexworks.mixite2.core.geometry.CubeCoordinate;
import org.hexworks.mixite2.core.geometry.Hexagon;

import java.util.Objects;

public class GridCell
{
    private final CubeCoordinate coordinate;
    private final Hexagon hexagon;

    public GridCell(CubeCoordinate coordinate, Hexagon hexagon)
    {
        this.coordinate = coordinate;
        this.hexagon = hexagon;
    }

    public CubeCoordinate getCoordinate()
    {
        return coordinate;
    }

    public Hexagon getHexagon()
    {
        return hexagon;
    }

    public boolean equals(int otherGridX, int otherGridZ) {
        return coordinate.equals(otherGridX, otherGridZ);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof GridCell gridCell)) return false;
        return Objects.equals(getCoordinate(), gridCell.getCoordinate());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getCoordinate());
    }
}