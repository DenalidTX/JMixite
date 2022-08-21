package org.hexworks.jmixite.core.grid.layout;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class HexagonalGridLayoutTest {

    @Test
    public void shouldBeHexagonalGridLayoutWhenGetGridLayoutStrategyFromHexagonalIsCalled() {
        assertInstanceOf(HexagonalGridLayoutStrategy.class, HexagonalGridLayout.HEXAGONAL.getGridLayoutStrategy());
    }

    @Test
    public void shouldBeRectangularGridLayoutWhenGetGridLayoutStrategyFromRectangularIsCalled() {
        assertInstanceOf(RectangularGridLayoutStrategy.class, HexagonalGridLayout.RECTANGULAR.getGridLayoutStrategy());
    }

    @Test
    public void shouldBeTrapezoidGridLayoutWhenGetGridLayoutStrategyFromTrapezoidIsCalled() {
        assertInstanceOf(TrapezoidGridLayoutStrategy.class, HexagonalGridLayout.TRAPEZOID.getGridLayoutStrategy());
    }

    @Test
    public void shouldBeTriangularGridLayoutWhenGetGridLayoutStrategyFromTriangularIsCalled() {
        assertInstanceOf(TriangularGridLayoutStrategy.class, HexagonalGridLayout.TRIANGULAR.getGridLayoutStrategy());
    }

}
