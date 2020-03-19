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
    val bestPairResponse = divideAndFindClosestPairOfPoints(points.sortedBy { it.x }, 0, points.lastIndex,
            points.sortedBy { it.y })
    return Pair(bestPairResponse.firstPoint, bestPairResponse.secondPoint)

}

private fun divideAndFindClosestPairOfPoints(sortedByX: List<Point>, first: Int, last: Int,
                                             sortedByY: List<Point>): ClosestPairResponse {
    if (last - first < 3) {
        return findClosestDistanceByBruteForce(sortedByX, first, last)
    }
    val mid = sortedByX.size / 2 - 1

    val closestLeft = divideAndFindClosestPairOfPoints(sortedByX, first, mid, sortedByY)
    val closestRight = divideAndFindClosestPairOfPoints(sortedByX, mid + 1, sortedByX.lastIndex, sortedByY)
    return ClosestPairResponse(closestLeft.firstPoint, closestLeft.secondPoint, closestLeft.distance)
}

private fun mergeListByYCoordinates(dividingPoint: Double, sortedByY: List<Point>) {

}

private fun findClosestDistanceByBruteForce(points: List<Point>, first: Int, last: Int): ClosestPairResponse {
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
    return (firstPoint.x - secondPoint.x).toDouble().pow(2.00) +
            (firstPoint.y - secondPoint.y).toDouble().pow(2.00)
}

private class ClosestPairResponse(val firstPoint: Point, val secondPoint: Point, val distance: Double)

