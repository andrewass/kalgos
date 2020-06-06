package algorithms.string

/**
 * Finding the longest palindromic substring of input string, by usage
 * of [Manacher's algorithm](https://en.wikipedia.org/wiki/Longest_palindromic_substring)
 */
fun longestPalindrome(source: String): String {
    val word = preProcessWord(source)
    val lcp = IntArray(word.length)
    var center = 0
    var rightEdge = 0

    for (i in 1 until word.length - 1) {
        val mirror = center * 2 - i
        if (i < rightEdge) {
            lcp[i] = (rightEdge - i).coerceAtMost(lcp[mirror])
        }
        while (word[i - 1 - lcp[i]] == word[i + 1 + lcp[i]]) {
            lcp[i]++
        }
        if (i + lcp[i] > rightEdge) {
            center = i
            rightEdge = i + lcp[i]
        }
    }
    return postProcessWord(lcp, word)
}

private fun preProcessWord(word: String): StringBuilder {
    val capacity = word.length * 2 + 1
    val preppedWord = StringBuilder("(")
    for (i in 0 until capacity) {
        if (i.rem(2) == 0) {
            preppedWord.append("#")
        } else {
            preppedWord.append(word[i / 2])
        }
    }
    return preppedWord.append(")")
}

private fun postProcessWord(lcp: IntArray, word: StringBuilder): String {
    var maxLen = 0
    var index = 0
    for (i in 1 until word.length) {
        if (lcp[i] > maxLen) {
            maxLen = lcp[i]
            index = i
        }
    }
    return word.substring(index - maxLen, index + maxLen).replace("#", "")
}

