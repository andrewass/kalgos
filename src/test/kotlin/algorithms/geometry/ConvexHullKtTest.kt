package algorithms.geometry

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConvexHullTest {

    @Test
    fun shouldReceiveHullPointsInCounterClockWiseOrder() {
        val points : List<Point> = mutableListOf(
                Point(1,-1),
                Point(0,0),
                Point(-1,0),
                Point(-1,1),
                Point(0,3),
                Point(2,2),
                Point(-1,-1),
                Point(1,1))

        val result = convexHull(points)

        assertEquals(5, result.size)

        assertEquals(points[6], result[0])
        assertEquals(points[0], result[1])
        assertEquals(points[5], result[2])
        assertEquals(points[4], result[3])
        assertEquals(points[3], result[4])
    }

    @Test
    fun shouldHandleDuplicatePoints(){
        val points : List<Point> = mutableListOf(
                Point(1,-1),
                Point(1,-1),
                Point(0,0),
                Point(0,0),
                Point(-1,-1),
                Point(-1,-1))

        val result = convexHull(points)

        assertEquals(3, result.size)

        assertEquals(-1, result[0].x)
        assertEquals(1, result[1].x)
        assertEquals(0, result[2].x)
    }

    @Test
    fun shouldHandleSinglePointInPlane(){
        val points : List<Point> = mutableListOf(Point(1,1))

        val result = convexHull(points)

        assertEquals(1, result.size)

        assertEquals(points[0], result[0])
    }

}