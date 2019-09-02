package algorithms.search

import java.util.*

/**
 * Find the
 * [Longest Increasing Subsequence](https://en.wikipedia.org/wiki/Longest_increasing_subsequence)
 * by using Binary Search.
 *
 * @param numbers Sequence to retrieve longest increasing subsequence from
 * @return a list storing the longest increasing subsequence
 */
fun longestIncSubSeq(numbers: List<Int>): List<Int> {
    val parent = IntArray(numbers.size)
    val sequence = IntArray(numbers.size + 1)
    var low = 0

    for (i in numbers.indices) {
        var start = 1
        var end = low

        while (start <= end) {
            val mid = (start + end) / 2
            when {
                numbers[sequence[mid]] <= numbers[i] -> start = mid + 1
                else -> end = mid - 1
            }
        }
        val newLow = start
        parent[i] = sequence[newLow - 1]
        sequence[newLow] = i
        low = Integer.max(low, newLow)
    }
    val lis = LinkedList<Int>()
    var largestRem = sequence[low]

    for (i in low - 1 downTo 0) {
        lis.addFirst(numbers[largestRem])
        largestRem = parent[largestRem]
    }
    return lis
}