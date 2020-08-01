package algorithms.geometry

import entities.Point
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class PolygonAreaTest{

    @Test
    fun shouldFindPolygonAreaWithPointsInClockWiseOrder(){
        val points = createPoints()

        val area = polygonArea(points)
        assertEquals(-3817.5, area)
    }

    @Test
    fun shouldFindPolygonAreaWithPointsInCounterClockWiseOrder(){
        val points = createPoints().reversed()

        val area = polygonArea(points)
        assertEquals(3817.5, area)
    }

    private fun createPoints() : List<Point> {
        return listOf(
                Point(41.00, -6.00),
                Point(-24.00, -74.00),
                Point(-51.00, -6.00),
                Point(73.00, 17.00),
                Point(-30.00, -34.00),
                Point(41.00, -6.00)
        )
    }
}