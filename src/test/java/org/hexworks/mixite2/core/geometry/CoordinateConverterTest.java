package org.hexworks.mixite2.core.geometry;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromAxialKey;
import static org.hexworks.mixite2.core.geometry.CubeCoordinate.fromCoordinates;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CoordinateConverterTest
{
    private final int TEST_X = 3;
    private final int TEST_Y = 4;
    private final String TEST_KEY = "7,8";
    private final int TEST_GRID_X = 7;
    private final int TEST_GRID_Z = 8;

    private final int EXPECTED_AXIAL_X_WITH_POINTY = 1;
    private final int EXPECTED_AXIAL_X_WITH_FLAT = 3;
    private final int EXPECTED_AXIAL_Z_WITH_POINTY = 4;
    private final int EXPECTED_AXIAL_Z_WITH_FLAT = 3;

    private List<CubeCoordinate> testCubes = Arrays.asList(
            CubeCoordinate.fromCoordinates(-1, -2),
            CubeCoordinate.fromCoordinates(2, -3),
            CubeCoordinate.fromCoordinates(7, 8)
    );

    private List<Integer> expectedOffsetColWithFlat   = Arrays.asList(-1, 2, 7);
    private  List<Integer> expectedOffsetRowWithFlat   = Arrays.asList(-3,-2,11);
    private  List<Integer> expectedOffsetColWithPointy = Arrays.asList(-2, 0,11);
    private  List<Integer> expectedOffsetRowWithPointy = Arrays.asList(-2,-3, 8);

    private static final CoordinateConverter coordConverter = new CoordinateConverter();


    @Test
    public void shouldConvertOffsetCoordinatesToAxialXWithPointy() {
        var result = coordConverter.convertOffsetCoordinatesToCubeX(TEST_X, TEST_Y, HexagonOrientation.POINTY_TOP);
        assertEquals(EXPECTED_AXIAL_X_WITH_POINTY, result);
    }

    @Test
    public void shouldConvertOffsetCoordinatesToAxialXWithFlat() {
        var result = coordConverter.convertOffsetCoordinatesToCubeX(TEST_X, TEST_Y, HexagonOrientation.FLAT_TOP);
        assertEquals(EXPECTED_AXIAL_X_WITH_FLAT, result);
    }

    @Test
    public void shouldConvertOffsetCoordinatesToAxialZWithPointy() {
        var result = coordConverter.convertOffsetCoordinatesToCubeZ(TEST_X, TEST_Y, HexagonOrientation.POINTY_TOP);
        assertEquals(EXPECTED_AXIAL_Z_WITH_POINTY, result);
    }

    @Test
    public void shouldConvertOffsetCoordinatesToAxialZWithFlat() {
        var result = coordConverter.convertOffsetCoordinatesToCubeZ(TEST_X, TEST_Y, HexagonOrientation.FLAT_TOP);
        assertEquals(EXPECTED_AXIAL_Z_WITH_FLAT, result);
    }

    @Test
    public void shouldConvertCubeCoordinatesToOffsetRowWithFlat() {
        for( int i=0; i<testCubes.size(); i++)
        {
            CubeCoordinate cube = testCubes.get(i);
            var result = coordConverter.convertCubeCoordinateToOffsetRow(cube, HexagonOrientation.FLAT_TOP);
            assertEquals(expectedOffsetRowWithFlat.get(i), result, "(" + cube.gridX() + "," + cube.gridZ() + ") to " + result);
        }
    }

    @Test
    public void shouldConvertCubeCoordinatesToOffsetColWithFlat() {
        for( int i=0; i<testCubes.size(); i++)
        {
            CubeCoordinate cube = testCubes.get(i);
            var result = coordConverter.convertCubeCoordinateToOffsetColumn(cube, HexagonOrientation.FLAT_TOP);
            assertEquals(expectedOffsetColWithFlat.get(i), result, "(" + cube.gridX() + "," + cube.gridZ() + ") to " + result);
        }
    }

    @Test
    public void shouldConvertCubeCoordinatesToOffsetRowWithPointy() {
        for( int i=0; i<testCubes.size(); i++)
        {
            CubeCoordinate cube = testCubes.get(i);
            var result = coordConverter.convertCubeCoordinateToOffsetRow(cube, HexagonOrientation.POINTY_TOP);
            assertEquals(expectedOffsetRowWithPointy.get(i), result, "(" + cube.gridX() + "," + cube.gridZ() + ") to " + result);
        }
    }

    @Test
    public void shouldConvertCubeCoordinatesToOffsetColWithPointy() {
        for( int i=0; i<testCubes.size(); i++)
        {
            CubeCoordinate cube = testCubes.get(i);
            var result = coordConverter.convertCubeCoordinateToOffsetColumn(cube, HexagonOrientation.POINTY_TOP);
            assertEquals(expectedOffsetColWithPointy.get(i), result, "(" + cube.gridX() + "," + cube.gridZ() + ") to " + result);
        }
    }

    @Test
    public void shouldCreateKeyFromCoordinate() {
        assertEquals(TEST_KEY, fromCoordinates(TEST_GRID_X, TEST_GRID_Z).toAxialKey());
    }

    @Test
    public void shouldCreateCoordinateFromKey() {
        var c = fromAxialKey(TEST_KEY);
        assertEquals(TEST_GRID_X, c.gridX());
        assertEquals(TEST_GRID_Z, c.gridZ());
    }
}
