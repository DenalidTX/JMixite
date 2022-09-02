package org.hexworks.jmixite.core.geometry;

/**
 * Utility class for converting coordinated from the offset system to
 * the cube one (the library uses the latter).
 */
public class CoordinateConverter
{
    /**
         * Calculates the cube X coordinate based on an offset coordinate pair.
         *
         * @param offsetX offset x
         * @param offsetY offset y
         * @param orientation orientation
         *
         * @return cube x
         */
        public int convertOffsetCoordinatesToCubeX(int offsetX, int offsetY, HexagonOrientation orientation)
        {
            return (HexagonOrientation.FLAT_TOP.equals(orientation))? offsetX : offsetX - offsetY / 2;
        }

        /**
         * Calculates the cube Z coordinate based on an offset coordinate pair.
         *
         * @param offsetX offset x
         * @param offsetY offset y
         * @param orientation orientation
         *
         * @return cube z
         */
        public int convertOffsetCoordinatesToCubeZ(int offsetX, int offsetY, HexagonOrientation orientation)
        {
            return (HexagonOrientation.FLAT_TOP.equals(orientation))? offsetY - offsetX / 2 : offsetY;
        }

        /**
         * Calculates the offset row based on a CubeCoordinate.
         *
         * @Param cubeCoordinate a cube coordinate
         * @param orientation orientation
         *
         * @return offset row or y-value
         */
        public int convertCubeCoordinateToOffsetRow(CubeCoordinate coordinate, HexagonOrientation orientation)
        {
            if(HexagonOrientation.FLAT_TOP.equals(orientation)) {
            return coordinate.z() + (coordinate.x() - (coordinate.x() & 1)) / 2;
            }
            else
            {
               return coordinate.z();
            }
        }

        /**
         * Calculates the offset column based on a CubeCoordinate.
         *
         * @Param cubeCoordinate a cube coordinate
         * @param orientation orientation
         *
         * @return offset column or x-value
         */
        public int convertCubeCoordinateToOffsetColumn(CubeCoordinate coordinate, HexagonOrientation orientation) {
            if(HexagonOrientation.FLAT_TOP.equals(orientation))
            {
                return coordinate.x();
            }
            else
            {
                return coordinate.x() + (coordinate.z() - (coordinate.z() & 1)) / 2;
            }
        }

}
