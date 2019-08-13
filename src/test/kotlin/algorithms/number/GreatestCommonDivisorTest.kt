package algorithms.number

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GreatestCommonDivisorTest{

    @Test
    fun shouldReturnCorrectGcdOfTwoDistinctIntegers(){
        val gcd = gcd(3434,234)
        assertEquals(2, gcd)
    }

    @Test
    fun shouldReturnOutputValueIdenticalToInputWhenInputPairIsEqual(){
        val gcd = gcd(43,43)
        assertEquals(43, gcd)
    }

    @Test
    fun shouldReturnOutputValueOneWhenInputArePrimeNumbers(){
        val gcd = gcd(83, 113)
        assertEquals(1, gcd)
    }

    @Test
    fun shouldReturnOutputValueOneWhenInputNumbersAreCoPrime(){
        val gcd = gcd(84, 65)
        assertEquals(1, gcd)
    }


}