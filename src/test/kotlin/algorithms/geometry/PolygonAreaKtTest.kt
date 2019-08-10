package algorithms.geometry

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
                Point(41, -6),
                Point(-24,-74),
                Point(-51, -6),
                Point(73, 17),
                Point(-30, -34),
                Point(41, -6)
        )
    }
}