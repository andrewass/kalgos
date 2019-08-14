package algorithms.number

import utils.product
import java.math.BigInteger


/**
 * [Chinese Remainder Theorem](https://en.wikipedia.org/wiki/Chinese_remainder_theorem)
 *
 * @param vals List of numbers serving as the remainder values
 * @param modVals List of co-prime divisors
 * @return the remainder satisfying all equations
 */
fun crm(vals : List<Long>, modVals : List<Long>) : Long{
    val modProduct = modVals.product()
    var res = 0L
    for(i in vals.indices){
        val gcd = extendedEuclid(modVals[i], modProduct/modVals[i])
        val a = BigInteger(vals[i].toString())
        val b = BigInteger(gcd[2].toString())
        val c = BigInteger((modProduct/modVals[i]).toString())
        val mod = BigInteger(modProduct.toString())
        res += a.multiply(b).multiply(c).remainder(mod).toLong()
        res = (res.rem(modProduct) + modProduct).rem(modProduct)
    }
    return  res
}