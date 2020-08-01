package entities

//Todo: Complete implementation of class
class LineSegment(private val firstPoint: Point, private val secPoint: Point) {

    infix fun intersects(other: LineSegment): Int {
        val dir1 = direction(other.firstPoint, other.secPoint, this.firstPoint)
        val dir2 = direction(other.firstPoint, other.secPoint, this.secPoint)
        val dir3 = direction(this.firstPoint, this.secPoint, other.firstPoint)
        val dir4 = direction(this.firstPoint, this.secPoint, other.secPoint)
        return 0
    }

    /**
     * Calculating the direction using the cross product
     */
    private fun direction(firstPoint: Point, secPoint: Point, thirdPoint: Point): Double {
        return (thirdPoint.x - firstPoint.x) * (secPoint.y - firstPoint.y) -
                (secPoint.x - firstPoint.x) * (thirdPoint.y - firstPoint.y)

    }

}