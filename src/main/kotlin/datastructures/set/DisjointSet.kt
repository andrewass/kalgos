package datastructures.set

/**
 * Implementation of the the
 * [Disjoint SetNode / Union Find](https://en.wikipedia.org/wiki/Disjoint-set_data_structure) data structure.
 */
class DisjointSet {

    private val sets = mutableListOf<SetNode>()

    fun addSet(setNode: SetNode) {
        sets.add(setNode)
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
            setX.rank > setY.rank -> {
                setY.parent = setX
                setX.size += setY.size
            }
            else -> {
                setX.parent = setY
                setY.size += setX.size
                if(setY.rank == setX.rank){
                    setY.rank++
                }
            }
        }
    }

    /**
     * Finds the root node of the set which an item with a given id belongs
     *
     * @id Id of the node
     */
    fun find(id: Int): SetNode {
        if (sets[id].parent.id != sets[id].id) {
            sets[id].parent = find(sets[id].parent.id)
        }
        return sets[id].parent
    }
}
