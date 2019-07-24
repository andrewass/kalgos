package algorithms.string


/**
 * Implementation of the [Rabin Karp algorithm](https://en.wikipedia.org/wiki/Rabin%E2%80%93Karp_algorithm)
 * , where string comparisons are based on their hash values
 *
 * @param word Word which may contain the given pattern
 * @param pattern Pattern which may occur as multiple substrings in the word
 */
fun rabinKarp(word: String, pattern: String): List<Int> {
    val matches = mutableListOf<Int>()
    if (pattern.length > word.length) {
        return matches
    }

    val modValue = 1000000009L
    val primePowers = getPrimePowers(length = word.length, modValue = modValue)

    var hash = LongArray(word.length + 1)
    for (i in word.indices) {
        hash[i + 1] = (hash[i] + (word[i] - 'A' + 1) * primePowers[i]).rem(modValue)
    }

    var patternHash = 0L
    for (i in pattern.indices) {
        patternHash = (patternHash + (pattern[i] - 'A' + 1) * primePowers[i]).rem(modValue)
    }

    val searchLength = word.length - pattern.length
    for (i in 0..searchLength) {
        val currHash = (hash[i + pattern.length] + modValue - hash[i]).rem(modValue)
        if (currHash == (patternHash * primePowers[i]).rem(modValue)) {
            matches.add(i)
        }
    }
    return matches
}


/**
 * Calculate the prime powers to be used in rabin karp algorithm
 *
 * @param length Number of prime powers
 * @param prime Prime number used to generate the numbers
 * @param modValue Value used as modulo value
 * @return an array containg the prime powers
 */
private fun getPrimePowers(length: Int, prime: Long = 31L, modValue: Long): LongArray {
    val primePowers = LongArray(length)
    primePowers[0] = 1L
    for (i in 1 until length) {
        primePowers[i] = (primePowers[i - 1] * prime).rem(modValue)
    }
    return primePowers
}

