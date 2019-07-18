package datastructures.set

/**
 * Implementation of the the
 * [Disjoint Set / Union Find](https://en.wikipedia.org/wiki/Disjoint-set_data_structure) data structure.
 */
class DisjointSet {

    private val sets = mutableListOf<Set>()

    fun addSet(set: Set) {
        sets.add(set)
    }

    /**
     * Merge two sets into one. The smallest set is merged into the largest set.
     *
     * @param x Id of the first set
     * @param y Id of the second set
     */
    fun union(x: Int, y: Int) {
        val setX = find(x)
        val setY = find(y)

        when {
            setX == setY -> return
            setX.size < setY.size -> {
                setX.parent = setY
                setY.size += setX.size
                setY.sum += setX.sum
                setX.sum = 0L
                setX.size = 0
            }
            else -> {
                setY.parent = setX
                setX.size += setY.size
                setX.sum += setY.sum
                setY.sum = 0L
                setY.size = 0
            }
        }
    }

    /**
     * Finds the root node of the set which a node with a given id belongs
     *
     * @id Id of the node
     */
    fun find(id: Int): Set {
        if (sets[id].parent.id != id) {
            sets[id].parent = find(sets[id].parent.id)
        }
        return sets[id].parent
    }
}
