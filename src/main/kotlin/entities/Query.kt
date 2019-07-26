package entities

/**
 * A class representing a query performed within a given range.
 * Used when pre processing queries using Mo's Algorithm
 */
class Query(val left : Int, val right : Int, val group : Int) : Comparable<Query>  {

    override fun compareTo(other: Query): Int {
        return when (group) {
            other.group -> right - other.right
            else -> group - other.group
        }
    }
}