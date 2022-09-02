package org.hexworks.jmixite.core.geometry;

/**
 * Represents a right or left angle (60°) of a Hexagon rotation.
 * See: http://www.redblobgames.com/grids/hexagons/#rotation
 */
public enum RotationDirection
{

    RIGHT(coord -> CubeCoordinate.fromCoordinates(-coord.z(), -coord.y())),
    LEFT(coord -> CubeCoordinate.fromCoordinates(-coord.y(), -coord.x()));

    private interface RotationCalculator {
        CubeCoordinate calculate(CubeCoordinate coord);
    }
    private final RotationCalculator rotationCalculator;

    RotationDirection(RotationCalculator rotationCalculator)
    {
        this.rotationCalculator = rotationCalculator;
    }

    /**
     * Calculates a rotation (right or left) of `coord`.
     *
     * @param coord coordinate to rotate
     *
     * @return rotated coordinate
     */
    public CubeCoordinate calculateRotation(CubeCoordinate coord)
    {
        return rotationCalculator.calculate(coord);
    }

}
