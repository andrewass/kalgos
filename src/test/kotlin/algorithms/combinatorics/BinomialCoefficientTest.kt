package algorithms.combinatorics

import org.junit.jupiter.api.Test
import utils.createFactorialList
import java.math.BigInteger
import kotlin.test.assertEquals

class BinomialCoefficientTest {

    @Test
    fun shouldFindCorrectBinomialCoefficient(){
        val factorials = createFactorialList(10)

        assertEquals(BigInteger("3"), binomialCoefficient(3,1, factorials))
        assertEquals(BigInteger("1"), binomialCoefficient(5,0, factorials))
        assertEquals(BigInteger("210"), binomialCoefficient(10,6, factorials))
        assertEquals(BigInteger("10"), binomialCoefficient(5,2, factorials))
    }
}