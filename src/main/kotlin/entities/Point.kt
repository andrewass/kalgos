package entities

class Point(val x: Double, val y: Double) {
    var polarAngle = 0.00
    var anchorDistance = 0.00

    override fun toString(): String {
        return "$x $y"
    }
}