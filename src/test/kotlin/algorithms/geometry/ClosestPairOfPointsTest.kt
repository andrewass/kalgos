package algorithms.geometry

import org.junit.jupiter.api.Test
import utils.euclideanDistance
import utils.round
import kotlin.test.assertEquals


class ClosestPairOfPointsTest {

    @Test
    fun shouldFindClosestPairOfPointsFromTwoPoints() {
        val result = closestPairOfPoints(
                listOf(
                        Point(1.12, 0.00),
                        Point(0.00, 0.51)
                )
        )
        assertEquals(result.first.x, 0.00)
        assertEquals(result.first.y, 0.51)
        assertEquals(result.second.x, 1.12)
        assertEquals(result.second.y, 0.00)
    }

    @Test
    fun shouldFindClosestPairOfPointsFromThreePoints() {
        val result = closestPairOfPoints(
                listOf(
                        Point(158.00, 12.00),
                        Point(123.00, 15.00),
                        Point(1859.00, -1489.00)
                )
        )
        assertEquals(result.first.x, 123.00)
        assertEquals(result.first.y, 15.00)
        assertEquals(result.second.x, 158.00)
        assertEquals(result.second.y, 12.00)
    }

    @Test
    fun shouldFindClosestPairOfPointsWhenEqualPoints() {
        val result = closestPairOfPoints(
                listOf(
                        Point(158.00, 12.00),
                        Point(3.00, 24.00),
                        Point(15.00, 1.00),
                        Point(25.49, 0.55),
                        Point(112.00, 223.00),
                        Point(6412.00, 430.00),
                        Point(240.00, 350.00),
                        Point(565.00, 1.00),
                        Point(3.00, 124.00),
                        Point(12.00, 10.00),
                        Point(123.00, 15.00),
                        Point(158.00, 12.00)
                )
        )
        assertEquals(result.first.x, 158.00)
        assertEquals(result.first.y, 12.00)
        assertEquals(result.second.x, 158.00)
        assertEquals(result.second.y, 12.00)
    }

    @Test
    fun shouldFindClosestPairOfPoints() {
        val result = closestPairOfPoints(
                listOf(
                        Point(2.00, 3.00),
                        Point(12.00, 30.00),
                        Point(40.00, 50.00),
                        Point(5.00, 1.00),
                        Point(50.49, 0.55),
                        Point(3.00, 24.00),
                        Point(15.00, 1.00),
                        Point(25.49, 0.55),
                        Point(112.00, 223.00),
                        Point(6412.00, 430.00),
                        Point(240.00, 350.00),
                        Point(565.00, 1.00),
                        Point(3.00, 124.00),
                        Point(12.00, 10.00),
                        Point(3.00, 4.00)
                )
        )
        val euclideanDistance = euclideanDistance(result.first, result.second)
        assertEquals(1.41421, euclideanDistance.round(5))
    }
}