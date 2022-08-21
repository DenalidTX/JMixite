package org.hexworks.jmixite.core.geometry;

/**
 * Enum representing the possible orientations of a [Hexagon]. The names
 * are self-explanatory.
 */
public enum HexagonOrientation
{

    POINTY_TOP(0.5f),
    FLAT_TOP(0f);

    float coordinateOffset;

    HexagonOrientation(float offset)
    {
        coordinateOffset = offset;
    }

    public float getCoordinateOffset()
    {
        return coordinateOffset;
    }
}
