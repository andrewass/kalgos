package utils

import entities.Point
import kotlin.math.pow
import kotlin.math.sqrt


fun euclideanDistance(firstPoint: Point, secondPoint: Point) =
        sqrt((firstPoint.x - secondPoint.x).pow(2.00) +
                (firstPoint.y - secondPoint.y).pow(2.00))