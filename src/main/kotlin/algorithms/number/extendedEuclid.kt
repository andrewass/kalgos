package algorithms.number


/**
 * [Extended Euclidean algorithm](https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm)
 * Calculates both the gcd(a,b) and ax + by = gcd(a,b)
 *
 * @param a First number
 * @param b Second number
 * @return an array containing in given order the gcd d, and the coefficients x and y
 */
fun extendedEuclid(a: Long, b: Long): LongArray {
    return if (b == 0L) {
        longArrayOf(a, 1L, 0L)
    } else {
        val res = extendedEuclid(b, a.rem(b))
        longArrayOf(res[0], res[2], res[1] - (a / b) * res[2])
    }
}