package algorithms.geometry

import entities.Point

/**
 * Calculates the area of a polygon from a list of points.
 * The points must be ordered, either clockwise or counter-clockwise.
 * The first and last node of the list must be the same.
 * If ordered clockwise, the area will be a negative value, else positive.
 *
 * @param points List of points of the polygon
 * @return the area of the polygon
 */
fun polygonArea(points: List<Point>): Double {
    var area = 0.00
    for (i in 0..points.size - 2) {
        area += (points[i].x * points[i + 1].y - points[i + 1].x * points[i].y)
    }
    return area / 2
}