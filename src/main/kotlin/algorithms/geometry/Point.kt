package algorithms.geometry

class Point(val x: Long, val y: Long) {
    var polarAngle = 0.00
    var anchorDistance = 0.00

    override fun toString(): String {
        return "$x $y"
    }
}