package algorithms.search

import java.util.*


/**
 * Implementation of the Meet in the Middle search technique.
 * Used for finding max remainder sum, divided by mod val, of a sequence of numbers from the input list.
 *
 * Divides input list in two equal parts. Performs brute force summation for max sum in each part
 * Then use binary search to search for max sum combining sums from each part.
 */
fun meetInTheMiddle(numbers: List<Long>, modVal: Long) : Long {
    val firstModSumList = mutableListOf<Long>()
    val secondModSumList = mutableListOf<Long>()

    val modNumbers = numbers.map { it.rem(modVal) }
    if (modNumbers.size == 1) {
        return modNumbers.first()
    }
    val firstList = modNumbers.subList(0, modNumbers.size / 2)
    val secondList = modNumbers.subList(modNumbers.size / 2, modNumbers.size)

    bruteForceModSums(0, firstList, modVal, firstModSumList, firstList.first())
    bruteForceModSums(0, firstList, modVal, firstModSumList, 0L)
    bruteForceModSums(0, secondList, modVal, secondModSumList, secondList.first())
    bruteForceModSums(0, secondList, modVal, secondModSumList, 0L)

    return maxModuloSumBetweenList(firstModSumList, secondModSumList, modVal)
}

private fun bruteForceModSums(index: Int, modNumbers: List<Long>, modVal: Long, modSumList: MutableList<Long>, sum: Long) {
    if (index == modNumbers.size - 1) {
        modSumList.add(sum.rem(modVal))
        return
    }
    bruteForceModSums(index + 1, modNumbers, modVal, modSumList, sum + modNumbers[index + 1])
    bruteForceModSums(index + 1, modNumbers, modVal, modSumList, sum)
}

private fun maxModuloSumBetweenList(firstModSumList: List<Long>, secondModSumList: List<Long>, modVal : Long): Long {
    val firstModuloSumSet = TreeSet(firstModSumList)
    val secondModuloSumSet = TreeSet(secondModSumList)
    var maxSum = (firstModuloSumSet.last()).coerceAtLeast(secondModuloSumSet.last())

    firstModuloSumSet.forEach {
        val gap: Long = modVal.minus(it + 1)
        val floorValue = secondModuloSumSet.floor(gap)
        if (floorValue != null) {
            maxSum = maxSum.coerceAtLeast((it + floorValue).rem(modVal))
        }
    }
    return maxSum
}

