package utils

import java.math.BigInteger
import java.math.BigInteger.ONE


/**
 * Create a list of factorials. The factorial of a number "i" found
 * at index "i" of the list
 *
 * @param range Upper range of the list
 * @return a list of factorials
 */
fun createFactorialList(range : Int) : List<BigInteger>{

    val factorials = mutableListOf<BigInteger>(ONE, ONE)
    for(i in 2..range){
        factorials.add(factorials[i-1].multiply(BigInteger(i.toString())))
    }
    return factorials
}