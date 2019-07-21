package datastructures.rangequery

/**
 * An implementation of a Binary Indexed Tree / Fenwick Tree. Used to
 * find the sum of a range between two indices. The internal array is 1-indexed
 *
 * @param capacity holds the capacity of the tree
 */
class BinaryIndexedTree(private val capacity: Int) {

    private val tree = LongArray(capacity + 1)

    /**
     * Finds the sum of the range from 1 to a given ind inclusive
     *
     * @param ind representing the upper bound of the range
     * @return the sum of the range between 1 and ind inclusive
     */
    fun getSum(ind: Int): Long {
        validateInput(ind)
        var index = ind
        var sum = 0L
        while (index >= 1) {
            sum += tree[index]
            index -= index and -index
        }
        return sum
    }

    /**
     * Adds a given value to element at ind, and updates up to log N succeeding elements
     *
     * @param ind representing the element to have its value updated
     * @param value representing the value to be updated at the ind. May be both positive and negative
     */
    fun addValue(ind: Int, value: Long) {
        validateInput(ind)
        var index = ind
        while (index <= capacity) {
            tree[index] += value
            index += index and -index
        }
    }

    /**
     * Validates the index to be used in the internal array. Throws [IllegalArgumentException] if input
     * is invalid
     *
     * @param index used for insertion or deletion from the tree
     * @throws [IllegalArgumentException] if index is 0 or greater than array capacity.
     */
    private fun validateInput(index: Int) {
        if (index < 1 || index > capacity) {
            throw IllegalArgumentException("Index must be within 1 and tree capacity inclusive. Actual index : $index")
        }
    }
}