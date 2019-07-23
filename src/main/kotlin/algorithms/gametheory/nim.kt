package algorithms.gametheory

/**
 * Decides if the player at a give state of a nim game will win or lose. This is decided by the xor sum of
 * the grundy number of each heap. An xor sum of 0 means the player will lose. Any positive value means current player
 * will win.
 *
 * @param heaps A list storing the size of each heap
 * @param removes A list of valid amount of items which can be removed at any turn of the game
 * @param memo A memoization list used to avoid recalculations of grundy numbers
 * @param minVal The minimum amount of items on a heap for any move to be possible
 * @return "W" if current player wins, else "L"
 */
fun decideNimWinner(heaps: List<Int>, removes: List<Int> = emptyList(), memo: Array<Int> = emptyArray(), minVal: Int = 1): String {
    var xorSum = 0
    for (heap in heaps) {
        xorSum = if (removes.isEmpty()) {
            xorSum xor heap
        } else {
            xorSum xor getGrundyNumber(heap, removes, memo, minVal)
        }
    }
    return if (xorSum == 0) "L" else "W"
}

/**
 * Get the grundy number for any state of a given heap, based on
 * [Sprague–Grundy theorem](https://en.wikipedia.org/wiki/Sprague%E2%80%93Grundy_theorem).
 *
 * @param itemsInHeap Remaining items in the heap
 * @param removesPerTurn A list of valid amount of items which can be removed at any turn of the game
 * @param memo A memoization list used to avoid recalculations of grundy numbers
 * @param minVal The minimum amount of items on a heap for any move to be possible
 * @return the grundy number of the given state of a heap
 */
private fun getGrundyNumber(itemsInHeap: Int, removesPerTurn: List<Int>, memo: Array<Int>, minVal: Int): Int {
    if (itemsInHeap < minVal) {
        return 0
    }
    if (memo[itemsInHeap] != -1) {
        return memo[itemsInHeap]
    }
    val results = mutableSetOf<Int>()
    for (removeCount in removesPerTurn) {
        val remaining = itemsInHeap - removeCount
        if (remaining >= 0) {
            results.add(getGrundyNumber(remaining, removesPerTurn, memo, minVal))
        }
    }
    val result = calculateMex(results)
    memo[itemsInHeap] = result

    return result
}

/**
 * Calculate the minimum excluded value of a given set, also known as
 * [Mex value](https://en.wikipedia.org/wiki/Mex_(mathematics))
 *
 * @param set A set of non-negative integers
 * @return smallest non-negative integer not present in the set
 */
private fun calculateMex(set: Set<Int>): Int {
    val sortedPositiveList = set.sorted()
    val posNumbersList = (0 until sortedPositiveList.size).toList()
    for (i in posNumbersList.indices) {
        if (sortedPositiveList[i] != posNumbersList[i]) {
            return i
        }
    }
    return posNumbersList.size
}