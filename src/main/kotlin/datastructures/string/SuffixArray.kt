package datastructures.string

import java.util.*
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.max

/**
 * Data structure representing a [Suffix Array](https://en.wikipedia.org/wiki/Suffix_array)
 *
 * @param word Word used when constructing the array
 */
class SuffixArray(private val word: String) {

    private val n = word.length
    private val sa = Array(n) { Node() }
    private val lcp = IntArray(n)

    init {
        constructSuffixArray()
    }

    /**
     * Get the lcp array
     *
     * @return lcp array
     */
    fun getlcp() = lcp

    /**
     * Get the index of the word for a given index of the suffix array
     *
     * @param index Index of the suffix array
     * @return corresponding index of the word
     */
    fun at(index: Int) = sa[index].pos

    /**
     * Get the longest common prefix for a given index
     *
     * @param index Index of the lcp array
     * @return corresponding longest common prefix found at the index of the lcp array
     */
    fun lcpAt(index: Int) = lcp[index]

    /**
     * Construct the suffix array
     */
    private fun constructSuffixArray() {
        var count = 1
        val logCeil = ceil(log2(n.toDouble())).toInt()

        val grid = Array(logCeil + 1) { IntArray(n) }
        val minChar = word.min()
        for (i in word.indices) {
            grid[0][i] = word[i] - minChar!!
        }

        for (k in 1..logCeil) {
            for (i in 0 until n) {
                sa[i].rank[0] = grid[k - 1][i]
                sa[i].rank[1] = if (i + count < word.length) grid[k - 1][i + count] else -1
                sa[i].pos = i
            }
            Arrays.sort(sa)

            for (i in 0 until n) {
                grid[k][sa[i].pos] =
                        if (i > 0 && sa[i].rank[0] == sa[i - 1].rank[0] && sa[i].rank[1] == sa[i - 1].rank[1])
                            grid[k][sa[i - 1].pos] else i
            }
            count *= 2
        }
    }

    /**
     * Construct the LCP array with Kasai et al. algorithm
     */
    fun constructLcpArray() {
        val inverse = IntArray(n)

        for (i in 0 until n) {
            inverse[sa[i].pos] = i
        }
        var len = 0
        for (i in 0 until n) {
            if (inverse[i] > 0) {
                val k = sa[inverse[i] - 1].pos
                while (i + len < n && k + len < n && word[i + len] == word[k + len]) {
                    len++
                }
                lcp[inverse[i]] = len
                len = max(len - 1, 0)
            }
        }
    }

    /**
     * Get the length of the longest common substring of the word
     *
     * @return length of the longest common substring
     */
    fun getLongestCommonSubstringLength(): Int {
        return lcp.max() ?: 0
    }

    /**
     * Get all the longest common substrings found in the word
     *
     * @return set of all the longest common substrings
     */
    fun getLongestCommonSubstrings(): Set<String> {
        val maxLen = getLongestCommonSubstringLength()
        val longestSubstrings = mutableSetOf<String>()
        if (maxLen > 0) {
            for (i in lcp.indices) {
                if (lcp[i] == maxLen) {
                    val substring = word.substring(sa[i].pos, sa[i].pos + maxLen)
                    longestSubstrings.add(substring)
                }
            }
        }
        return longestSubstrings
    }

    override fun toString(): String {
        val result = StringBuilder()
        for (i in sa.indices) {
            result.append("LCP : ${lcp[i]} , word ind : ${sa[i]}  ${word.substring(sa[i].pos)} \n")
        }
        return result.toString()
    }

    /**
     * Representing the suffix array entities. The nodes are sorted by their rank.
     */
    private class Node : Comparable<Node> {
        var pos = 0
        var rank = IntArray(2)

        override fun compareTo(other: Node): Int {
            if (rank[0] == other.rank[0]) {
                return rank[1] - other.rank[1]
            }
            return rank[0] - other.rank[0]
        }

        override fun toString(): String {
            return pos.toString()
        }
    }
}
