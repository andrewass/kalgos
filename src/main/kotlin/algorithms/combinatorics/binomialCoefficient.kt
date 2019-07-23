package algorithms.combinatorics

import java.math.BigInteger

/**
 * Returns the [Binomial Coefficient](https://en.wikipedia.org/wiki/Binomial_coefficient),
 * the number of ways we can choose a subset of "k" from the set "n".
 *
 * @param n Size of the set
 * @param k Size of the subset
 * @param factorials List of pre-calculated factorials
 * @return the result as a [BigInteger]
 */
fun binomialCoefficient(n: Int, k: Int, factorials : List<BigInteger>): BigInteger {
    val numerator = factorials[n]
    val denominator = factorials[k].multiply(factorials[n-k])

    return numerator.divide(denominator)
}