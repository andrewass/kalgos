package algorithms.geometry

import org.junit.jupiter.api.Test
import utils.euclideanDistance
import utils.round
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class ClosestPairOfPointsTest {

    @Test
    fun shouldFindClosestPairOfPoints() {
        val result = closestPairOfPoints(
                listOf(
                        Point(2.00, 3.00),
                        Point(12.00, 30.00),
                        Point(40.00, 50.00),
                        Point(5.00, 1.00),
                        Point(5.49, 0.55),
                        Point(3.00, 24.00),
                        Point(40.00, 50.00),
                        Point(15.00, 1.00),
                        Point(25.49, 0.55),
                        Point(112.00, 223.00),
                        Point(6412.00, 430.00),
                        Point(240.00, 350.00),
                        Point(565.00, 1.00),
                        Point(5.49, 0.55),
                        Point(3.00, 124.00),
                        Point(12.00, 10.00),
                        Point(3.00, 4.00)
                )
        )
        val euclideanDistance = euclideanDistance(result!!.first, result!!.second)
        assertEquals(1.41421, euclideanDistance.round(5))
    }
}