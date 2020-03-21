package algorithms.geometry

import kotlin.math.pow

/**
 * Find the closest pair of points from a list of points
 *
 * @param points A list with a points
 * @return a pair of the two closest points
 */
fun closestPairOfPoints(points: List<Point>): Pair<Point, Point>? {
    if (points.size < 2) {
        return null
    }
    val bestPairResponse = divideAndFindClosestPairOfPoints(points.sortedBy { it.x }, points.sortedBy { it.y })
    return Pair(bestPairResponse.firstPoint, bestPairResponse.secondPoint)

}

private fun divideAndFindClosestPairOfPoints(sortedByX: List<Point>, sortedByY: List<Point>): ClosestPairResponse {
    if (sortedByX.size <= 3) {
        return findClosestDistanceByBruteForce(sortedByX)
    }
    val mid = sortedByX.size / 2
    val closestLeft = divideAndFindClosestPairOfPoints(sortedByX.subList(0, mid), sortedByY)
    val closestRight = divideAndFindClosestPairOfPoints(sortedByX.subList(mid, sortedByX.size), sortedByY)

    val sortedY = splitListByYCoordinates(sortedByX[mid].x, sortedByY)

    return ClosestPairResponse(closestLeft.firstPoint, closestLeft.secondPoint, closestLeft.distance)
}

private fun splitListByYCoordinates(dividingPoint: Double, sortedByY: List<Point>): Pair<List<Point>, List<Point>> {
    val leftList = mutableListOf<Point>()
    val rightList = mutableListOf<Point>()
    for (point in sortedByY) {
        if (point.x <= dividingPoint) {
            leftList.add(point)
        } else {
            rightList.add(point)
        }
    }
    return Pair(leftList, rightList)
}

private fun findClosestDistanceByBruteForce(points: List<Point>): ClosestPairResponse {
    var bestPair: ClosestPairResponse? = null
    for (firstPoint in points) {
        for (secondPoint in points) {
            if (firstPoint !== secondPoint) {
                val currDist = getDistance(firstPoint, secondPoint)
                if (bestPair == null || currDist < bestPair.distance) {
                    bestPair = ClosestPairResponse(firstPoint = firstPoint,
                            secondPoint = secondPoint, distance = currDist)
                }
            }
        }
    }
    return bestPair!!
}

private fun getDistance(firstPoint: Point, secondPoint: Point): Double {
    return (firstPoint.x - secondPoint.x).pow(2.00) +
            (firstPoint.y - secondPoint.y).pow(2.00)
}

private class ClosestPairResponse(val firstPoint: Point, val secondPoint: Point, val distance: Double)

