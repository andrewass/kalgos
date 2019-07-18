package datastructures.set

/**
 * Implementation of the the
 * [Disjoint SetNode / Union Find](https://en.wikipedia.org/wiki/Disjoint-set_data_structure) data structure.
 */
class DisjointSet {

    private val sets = mutableListOf<SetNode>()

    /**
     * A a new set to the list
     *
     * @param setNode The new set, storing an item.
     */
    fun addSet(setNode: SetNode) {
        sets.add(setNode)
    }

    /**
     * Get a the set corresponding to its index in the sets list
     *
     * @return the SetNode object stored at the given index
     */
    fun getSet(index : Int) = sets[index]


    /**
     * Moves a set node to the set containing another set node, marked as the the destination node.
     * Note : This does not remove the set node from its old set. Instead the old set has its sum and size
     * reduced.
     *
     * @param oldItem The set node used to identify the old set
     * @param destination The set node used to identify the new set
     * @param newItem A new representation of the set node to be moved. Merged into the destination set.
     */
    fun moveToSet(oldItem: SetNode, destination: SetNode, newItem : SetNode) {
        val rootNode = find(oldItem)
        val destinationRoot = find(destination)

        if (rootNode == destinationRoot) {
            return
        }
        rootNode.sum -= oldItem.id
        rootNode.size--

        sets[oldItem.id] = newItem
        union(newItem, destination)
    }

    /**
     * Merge two sets into one. The smallest set is merged into the largest set.
     *
     * @param x The first set
     * @param y The second set
     */
    fun union(x: SetNode, y: SetNode) {
        val rootX = find(x)
        val rootY = find(y)

        when {
            rootX == rootY -> return
            rootX.rank > rootY.rank -> {
                rootY.parent = rootX
                rootX.size += rootY.size
                rootX.sum += rootY.sum
            }
            else -> {
                rootX.parent = rootY
                rootY.size += rootX.size
                rootY.sum += rootX.sum
                if(rootY.rank == rootX.rank){
                    rootY.rank++
                }
            }
        }
    }

    /**
     * Finds the root set node from the set where current set node belongs
     *
     * @param curr Current set node
     * @return root set node of the set
     */
    fun find(curr: SetNode): SetNode {
        if (curr != curr.parent) {
            curr.parent = find(curr.parent)
        }
        return curr.parent
    }
}
