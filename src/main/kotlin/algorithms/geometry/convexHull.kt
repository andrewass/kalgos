package algorithms.geometry

import entities.Point
import java.util.*
import kotlin.math.pow

/**
 * Finding the Convex Hull of a set of points.
 * Implementing [Graham scan algorithm](https://en.wikipedia.org/wiki/Graham_scan)
 *
 * @param points List of all the points in the plane
 * @return list of all the points included in the Convex Hull, in counterclockwise order
 */
fun convexHull(points: List<Point>): List<Point> {
    val anchor = findLowestYPoint(points)

    val sortedPoints = getListSortedByPolarAngleAndDistance(anchor, points)

    val hull = LinkedList<Point>()
    hull.addFirst(anchor)

    if (sortedPoints.size < 2) {
        hull.addAll(sortedPoints)
        return hull
    }
    hull.addLast(sortedPoints[0])

    for (i in 1 until sortedPoints.size) {
        while (pointNotCausingCounterClockwiseTurn(sortedPoints[i], hull)) {
            hull.removeLast()
        }
        hull.addLast(sortedPoints[i])
    }
    return hull
}

/**
 * Testing if adding a new point to the hull causes a clockwise turn or is collinear.
 * @param point Current point to be added to the hull
 * @param hull List containing all the previous points added to the hull
 *
 * @return true if the addition of the current point does not cause a counterclockwise shift. Else false
 */
private fun pointNotCausingCounterClockwiseTurn(point: Point, hull: LinkedList<Point>): Boolean {
    val top = hull.removeLast()
    val prevTop = hull.last()
    hull.addLast(top)

    val firstProduct = (top.x - prevTop.x) * (point.y - prevTop.y)
    val secondProduct = (top.y - prevTop.y) * (point.x - prevTop.x)
    val zCoordinate = firstProduct - secondProduct

    return when {
        zCoordinate > 0.0000 -> false
        else -> true
    }
}

/**
 * Sort the list of points by their distance and polar angle relative to the anchor point.
 * If any two point are of equal polar degree, the one nearest the anchor is removed
 *
 * @param anchor The starting point of the hull
 * @param points List of points, including the anchor point
 * @return sorted list of points, excluding the anchor point
 */
private fun getListSortedByPolarAngleAndDistance(anchor: Point, points: List<Point>): List<Point> {
    for (point in points) {
        point.anchorDistance = (point.x - anchor.x).pow(2.00) + (point.y - anchor.y).pow(2.00)
        point.polarAngle = kotlin.math.atan2((point.y - anchor.y), (point.x - anchor.x))
    }

    return points.asSequence()
            .filter { !(it.x == anchor.x && it.y == anchor.y) }
            .sortedWith(compareBy<Point> { it.polarAngle }.thenByDescending { it.anchorDistance })
            .distinctBy { it.polarAngle }
            .toList()
}

/**
 * Find the point with the lowest y value. If multiple occurrences, sort by lowest x value.
 * Used to decide the anchor point
 *
 * @param points List of points in the plane
 * @return point to be used as anchor point when calculating the hull
 */
private fun findLowestYPoint(points: List<Point>): Point {
    var lowest = points[0]
    for (point in points) {
        when {
            point.y < lowest.y -> lowest = point
            point.y == lowest.y && point.x < lowest.x -> lowest = point
        }
    }
    return lowest
}