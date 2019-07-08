package datastructures.rangequeries

/**
 *
 */
class BinaryIndexedTree(private val capacity: Int) {

    private val tree = LongArray(capacity + 1)

    /**
     *
     */
    fun getSum(index: Int): Long {
        var indexCopy = index
        var sum = 0L
        while (indexCopy >= 1) {
            sum += tree[indexCopy]
            indexCopy -= indexCopy and -indexCopy
        }
        return sum
    }

    /**
     *
     */
    fun addValue(index: Int, value: Long) {
        var indexCopy = index
        while (indexCopy <= capacity) {
            tree[indexCopy] += value
            indexCopy += indexCopy and -indexCopy
        }
    }
}