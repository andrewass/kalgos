package datastructures.rangequeries

import java.lang.IllegalArgumentException

/**
 * An implementation of a Binary Indexed Tree / Fenwick Tree. Used to
 * find the sum of a range between two indices. The internal array is 1-indexed
 *
 * @param capacity holds the capacity of the tree
 */
class BinaryIndexedTree(private val capacity: Int) {

    private val tree = LongArray(capacity + 1)

    /**
     * Finds the sum of the range from 1 to a given index inclusive
     *
     * @param index representing the upper bound of the range
     * @return the sum of the range between 1 and index inclusive
     */
    fun getSum(index: Int): Long {
        validateInput(index)
        var indexCopy = index
        var sum = 0L
        while (indexCopy >= 1) {
            sum += tree[indexCopy]
            indexCopy -= indexCopy and -indexCopy
        }
        return sum
    }

    /**
     * Adds a given value to element at index, and updates up to log N succeeding elements
     *
     * @param index representing the element to have its value updated
     * @param value representing the value to be updated at the index. May be both positive and negative
     */
    fun addValue(index: Int, value: Long) {
        validateInput(index)
        var indexCopy = index
        while (indexCopy <= capacity) {
            tree[indexCopy] += value
            indexCopy += indexCopy and -indexCopy
        }
    }

    private fun validateInput(index: Int) {
        if (index < 1 || index > capacity) {
            throw IllegalArgumentException("Index must be within 1 and tree capacity inclusive. Actual index : $index")
        }
    }
}