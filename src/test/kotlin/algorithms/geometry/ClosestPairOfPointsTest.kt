package algorithms.geometry

import entities.Point
import org.junit.jupiter.api.Test
import utils.round
import kotlin.test.assertEquals


class ClosestPairOfPointsTest {

    @Test
    fun shouldFindClosestPairOfPointsFromInputFile1() {
        val rows = javaClass.getResource("/geometry/Closestpairofpoints1").readText().split("\r\n")
        val pointList = createPointsList(rows)
        val expectedResult = rows.last().toDouble()

        val result = closestPairOfPoints(pointList)

        assertEquals(expectedResult, result.distance.round(4))
    }

    @Test
    fun shouldFindClosestPairOfPointsFromInputFile2() {
        val rows = javaClass.getResource("/geometry/Closestpairofpoints2").readText().split("\r\n")
        val pointList = createPointsList(rows)
        val expectedResult = rows.last().toDouble()

        val result = closestPairOfPoints(pointList)

        assertEquals(expectedResult, result.distance.round(4))
    }

    @Test
    fun shouldFindClosestPairOfPointsFromInputFile3() {
        val rows = javaClass.getResource("/geometry/Closestpairofpoints3").readText().split("\r\n")
        val pointList = createPointsList(rows)
        val expectedResult = rows.last().toDouble()

        val result = closestPairOfPoints(pointList)

        assertEquals(expectedResult, result.distance.round(4))
    }


    @Test
    fun shouldFindClosestPairOfPointsFromTwoPoints() {
        val result = closestPairOfPoints(
                listOf(
                        Point(1.12, 0.00),
                        Point(0.00, 0.51)
                )
        )
        assertEquals(result.firstPoint.x, 0.00)
        assertEquals(result.firstPoint.y, 0.51)
        assertEquals(result.secondPoint.x, 1.12)
        assertEquals(result.secondPoint.y, 0.00)
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
        assertEquals(result.firstPoint.x, 123.00)
        assertEquals(result.firstPoint.y, 15.00)
        assertEquals(result.secondPoint.x, 158.00)
        assertEquals(result.secondPoint.y, 12.00)
    }

    @Test
    fun shouldFindClosestPairOfPointsWhenEqualPoints() {
        val result = closestPairOfPoints(
                listOf(
                        Point(158.00, 12.00),
                        Point(3.00, -24.00),
                        Point(-15.00, 1.00),
                        Point(25.49, 0.55),
                        Point(112.00, 223.00),
                        Point(-6412.00, 430.00),
                        Point(240.00, 350.00),
                        Point(565.00, 1.00),
                        Point(-3.00, -124.00),
                        Point(12.00, 10.00),
                        Point(123.00, -15.00),
                        Point(158.00, 12.00)
                )
        )
        assertEquals(result.firstPoint.x, 158.00)
        assertEquals(result.firstPoint.y, 12.00)
        assertEquals(result.secondPoint.x, 158.00)
        assertEquals(result.secondPoint.y, 12.00)
    }

    private fun createPointsList(rows : List<String>) : List<Point> {
        val pointList = mutableListOf<Point>()
        for(i in 1..rows.first().toInt()){
            val row = rows[i].split(" ")
            pointList.add(Point(row[0].toDouble(), row[1].toDouble()))
        }
        return pointList
    }
}