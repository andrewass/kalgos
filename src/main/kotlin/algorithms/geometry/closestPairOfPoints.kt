package algorithms.geometry

import kotlin.math.pow

/**
 * Find the closest pair of points from a list of points
 *
 * @param points A list with a points
 * @return a pair of the two closest points
 */
fun closestPairOfPoints(points: List<Point>): Pair<Point, Point>? {
    val bestPairResponse = divideAndFindClosestPairOfPoints(points.sortedBy { it.x }, points.sortedBy { it.y })
    return if (bestPairResponse == null || bestPairResponse.distance != Double.MAX_VALUE) {
        null
    } else {
        Pair(bestPairResponse.firstPoint, bestPairResponse.secondPoint)
    }

}

private fun divideAndFindClosestPairOfPoints(sortedByX: List<Point>, sortedByY: List<Point>): ClosestPairResponse? {
    if (sortedByX.size <= 3) {
        return findClosestDistanceByBruteForce(sortedByX)
    }
    val midPoint = sortedByX[sortedByX.size / 2 - 1]

    val closestLeft = divideAndFindClosestPairOfPoints(sortedByX.subList(0, sortedByX.size / 2), sortedByY)
    val closestRight = divideAndFindClosestPairOfPoints(sortedByX.subList(sortedByX.size / 2, sortedByX.size), sortedByY)
    return null
}

private fun mergeListByYCoordinates(dividingPoint : Double, sortedByY: List<Point>){
    
}

private fun findClosestDistanceByBruteForce(points: List<Point>): ClosestPairResponse? {
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
    return bestPair
}

private fun getDistance(firstPoint: Point, secondPoint: Point): Double {
    return (firstPoint.x - secondPoint.x).toDouble().pow(2.00) +
            (firstPoint.y - secondPoint.y).toDouble().pow(2.00)
}

private class ClosestPairResponse(val firstPoint: Point, val secondPoint: Point, val distance: Double)

