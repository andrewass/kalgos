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

private fun divideAndFindClosestPairOfPoints(sortedByXList: List<Point>, sortedByYList: List<Point>):
        ClosestPairResponse {
    if (sortedByXList.size <= 3) {
        return findClosestDistanceByBruteForce(sortedByXList)
    }
    val mid = sortedByXList.size / 2
    val ySplit = splitListByYCoordinates(sortedByXList[mid - 1].x, sortedByYList)
    val closestLeft = divideAndFindClosestPairOfPoints(sortedByXList.subList(0, mid), ySplit.first)
    val closestRight = divideAndFindClosestPairOfPoints(sortedByXList.subList(mid, sortedByXList.size), ySplit.second)

    val minDistLeftRightSide = findMinDistFromSetOfPairs(closestLeft, closestRight)
    val yCrossingMidList = crossListByYCoordinates(sortedByXList[mid - 1].x, sortedByYList, minDistLeftRightSide.distance)
    if (yCrossingMidList.size < 2) {
        return minDistLeftRightSide
    }
    val closestCrossingMid = findClosestPointCrossingMid(yCrossingMidList)

    return ClosestPairResponse(closestLeft.firstPoint, closestLeft.secondPoint, closestLeft.distance)
}

private fun findMinDistFromSetOfPairs(vararg  pairs : ClosestPairResponse) : ClosestPairResponse {

}



private fun findClosestPointCrossingMid(points: List<Point>): ClosestPairResponse? {
    var bestPair: ClosestPairResponse? = null
    for (i in 0 until points.size - 1) {
        for (i in i + 1 until points.size.coerceAtMost(i + 9)) {

        }
    }
    return bestPair!!

}

private fun crossListByYCoordinates(dividingPoint: Double, sortedByY: List<Point>, distance: Double): List<Point> {
    val crossList = mutableListOf<Point>()
    val leftLimit = dividingPoint - distance
    val rightLimit = dividingPoint + distance
    for (point in sortedByY) {
        if (point.x > leftLimit && point.x < rightLimit) {
            crossList.add(point)
        }
    }
    return crossList
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


