package org.hexworks.jmixite.core.geometry;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a cube coordinate pair.
 * See <a href="http://www.redblobgames.com/grids/hexagons/#coordinates">...</a> to learn more.
 * Note that the y coordinate is not stored in this object since it can be
 * calculated.
 */
public record CubeCoordinate (int x, int z)
{
    private static final String SEP = ",";

    public int y()
    {
        return -(x + z);
    }

    /**
     * Creates an axial (x, z) key which can be used in key-value storage objects based on this
     * [CubeCoordinate].
     *
     * @return key
     */
    public String toAxialKey()
    {
        return x + SEP + z;
    }


    public static CubeCoordinate fromAxialKey(String axialKey)
    {
        try {
            String[] coords = axialKey.split(SEP);

            List<String> coordsList = Arrays.stream(coords)
                    .filter(x -> !x.isBlank())
                    .toList();

            return fromCoordinates(Integer.parseInt(coordsList.get(0)), Integer.parseInt(coordsList.get(1)));
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("Failed to create CubeCoordinate from key: $axialKey", e);
        }
    }

    /**
     * Creates an instance of [CubeCoordinate] from an x and a z coordinate.
     *
     * @param gridX grid x
     * @param gridZ grid z
     *
     * @return coord
     */
    public static CubeCoordinate fromCoordinates(int gridX, int gridZ)
    {
        return new CubeCoordinate(gridX, gridZ);
    }

    public Point asCubicGridPoint()
    {
        // TODO: Verify that this has the correct effect.
        return Point.fromPosition(x, z);
    }

    public boolean equals(int otherGridX, int otherGridZ) {
        return (x == otherGridX) && (z == otherGridZ);
    }
}
