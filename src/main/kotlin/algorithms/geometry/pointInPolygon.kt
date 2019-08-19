package algorithms.geometry

import java.awt.Polygon
import java.awt.geom.Line2D
import java.awt.geom.Point2D

/**
 * Check if a given point is inside of polygon, on its perimeter or outside
 * of the polygon.
 *
 * @param polygon The polygon
 * @param x Position of point on the x-axis
 * @param y Position of point on the y-axis
 * @return "on" if on perimeter, "in" if inside or "out" if point is outside of the polygon
 */
fun pointInPolygon(polygon: Polygon, x: Int, y: Int): String {
    return when {
        pointIsOnLineSegment(polygon, x, y) -> "on"
        polygon.contains(x, y) -> "in"
        else -> "out"
    }
}

/**
 * Test if a given point lies on any of the line segments of the polygon
 *
 * @param polygon Polygon of points used to create line segments
 * @param x Position of point on the x-axis
 * @param y Position of point on the y-axis
 * @return true if point lies on any of the line segment, else false
 */
fun pointIsOnLineSegment(polygon: Polygon, x: Int, y: Int): Boolean {
    val point = Point2D.Double(x.toDouble(), y.toDouble())
    val n = polygon.npoints
    for (i in 0 until n) {
        val a = Point2D.Double(polygon.xpoints[i].toDouble(), polygon.ypoints[i].toDouble())
        val b = Point2D.Double(
                polygon.xpoints[(i + 1).rem(n)].toDouble(),
                polygon.ypoints[(i + 1).rem(n)].toDouble()
        )
        val line = Line2D.Double(a,b)
        if(line.ptSegDist(point) == 0.0){
            return true
        }
    }
    return false
}