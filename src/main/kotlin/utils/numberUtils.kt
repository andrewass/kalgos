package utils

import java.math.BigInteger
import java.math.BigInteger.ONE
import kotlin.math.round


/**
 * Create a list of factorials. The factorial of a number "i" found
 * at index "i" of the list
 *
 * @param range Upper range of the list
 * @return a list of factorials
 */
fun createFactorialList(range: Int): List<BigInteger> {

    val factorials = mutableListOf<BigInteger>(ONE, ONE)
    for (i in 2..range) {
        factorials.add(factorials[i - 1].multiply(BigInteger(i.toString())))
    }
    return factorials
}

fun Int.toBinaryString(len: Int): String {
    val binaryString = StringBuilder("")
    var powSum = 1 shl 30.coerceAtMost(len - 1)
    var remSum = this
    while (powSum > 0) {
        if (remSum >= powSum) {
            binaryString.append("1")
            remSum -= powSum
        } else {
            binaryString.append("0")
        }
        powSum = powSum shr 1
    }
    return binaryString.toString()
}

fun Double.round(decimals : Int) : Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}