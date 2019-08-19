package algorithms.geometry

import org.junit.jupiter.api.Test
import java.awt.Polygon
import kotlin.test.assertEquals

class PointInPolygonTest {

    private val outside = "out"
    private val inside = "in"
    private val on = "on"

    @Test
    fun shouldCorrectlyDecideIfPointIsInsideOutsideOrOnPolygonOfSize3() {
        val xPoints = intArrayOf(0, 10, 0)
        val yPoints = intArrayOf(0, 0, 10)
        val polygon = Polygon(xPoints, yPoints, 3)

        assertEquals(inside, pointInPolygon(polygon, 4, 5))
        assertEquals(on, pointInPolygon(polygon, 5, 5))
        assertEquals(outside, pointInPolygon(polygon, 6, 5))
    }

    @Test
    fun shouldCorrectlyDecideIfPointIsInsideOutsideOrOnPolygonOfSize5() {
        val xPoints = intArrayOf(41, -24, -51, 73, -30)
        val yPoints = intArrayOf(-6, -74, -6, 17, -34)
        val polygon = Polygon(xPoints, yPoints, 5)

        assertEquals(outside, pointInPolygon(polygon, -12, -26))
        assertEquals(inside, pointInPolygon(polygon, 39, -8))
    }
}