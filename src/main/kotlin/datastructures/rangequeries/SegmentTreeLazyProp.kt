package datastructures.rangequeries

/**
 * Segment Tree with Lazy Propagation. Current implementation finds the sum of a range.
 * Also supports range updates.
 */
class SegmentTreeLazyProp {

    private val tree: LongArray
    private val lazy: LongArray
    private val len: Int

    /**
     * Fills the segment tree, based on input array
     *
     * @param array Used to construct the initial tree
     */
    constructor(array: IntArray) {
        tree = LongArray(array.size * 4)
        lazy = LongArray(tree.size)
        len = array.size
        build(array = array)
    }

    /**
     * Creates an empty segment tree
     *
     * @param size Size of relevant range
     */
    constructor(size: Int) {
        tree = LongArray(size * 4)
        lazy = LongArray(tree.size)
        len = tree.size
    }

    /**
     * Builds the segment tree with values from input array.
     *
     * @param array Int array used to fill the tree
     * @param ind Current index / node of the segment tree
     * @param segLeft Left end of current segment
     * @param segRight Right end of current segment
     */
    private fun build(array: IntArray, ind: Int = 1, segLeft: Int = 1, segRight: Int = len) {
        if (segLeft == segRight) {
            tree[ind] = array[segLeft].toLong()
        } else {
            val mid = (segLeft + segRight) / 2
            build(array, ind * 2, segLeft, mid)
            build(array, ind * 2 + 1, mid + 1, segRight)
            tree[ind] = tree[ind * 2] + tree[ind * 2 + 1]
        }
    }

    /**
     * Updates the segment tree. Updates the nodes for a given range with a new value.
     *
     * @param ind Current index / node to update in the tree
     * @param value Adding this value to a given range
     * @param segLeft Left end of current segment
     * @param segRight Right end of current segment
     * @param left Left end of the update range
     * @param right Right end of the update range
     */
    fun update(ind: Int = 1, value: Long, segLeft: Int = 1, segRight: Int = len, left: Int, right: Int) {
        pushToChildren(ind, segLeft, segRight)
        if (segLeft > right || segRight < left || segLeft > segRight) {
            return
        }
        if (left <= segLeft && segRight <= right) {
            tree[ind] += value * (segRight - segLeft + 1).toLong()
            if (segLeft != segRight) {
                lazy[ind * 2] += value
                lazy[ind * 2 + 1] += value
            }
        } else {
            val mid = (segLeft + segRight) / 2
            update(ind * 2, value, segLeft, mid, left, right)
            update(ind * 2 + 1, value, mid + 1, segRight, left, right)
            tree[ind] = tree[ind * 2] + tree[ind * 2 + 1]
        }
    }

    /**
     * Get the sum of a given range in the tree.
     *
     * @param ind Current index / node of the segment tree
     * @param segLeft Left end of current segment
     * @param segRight Right end of current segment
     * @param left Left end of the query range
     * @param right Right end of the query range
     * @return sum of the range
     */
    fun get(ind: Int = 1, segLeft: Int = 1, segRight: Int = len, left: Int, right: Int): Long {
        if (segLeft > right || segRight < left || segLeft > segRight) {
            return 0L
        }
        pushToChildren(ind, segLeft, segRight)
        if (left <= segLeft && segRight <= right) {
            return tree[ind]
        }
        val mid = (segLeft + segRight) / 2
        val leftValue = get(ind * 2, segLeft, mid, left, right)
        val rightValue = get(ind * 2 + 1, mid + 1, segRight, left, right)
        return leftValue + rightValue
    }

    /**
     * Updates current node if current node is flagged as lazy, meaning
     * its lazy value is not zero. Laziness is then propagated from current node to its children
     *
     * @param ind Current index / node of the segment tree
     * @param segLeft Left end of current segment
     * @param segRight Right end of current segment
     */
    private fun pushToChildren(ind: Int, left: Int, right: Int) {
        if (lazy[ind] != 0L) {
            tree[ind] += lazy[ind] * (right - left + 1)
            if (left != right) {
                lazy[ind * 2] += lazy[ind]
                lazy[ind * 2 + 1] += lazy[ind]
            }
            lazy[ind] = 0L
        }
    }
}