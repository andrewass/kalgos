package algorithms.string

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HammingDistanceTest {

    @Test
    fun shouldReturnMostUniqueBinaryStringComparedWithAnyInTheList() {
        val binaryStrings = listOf("01001", "11100", "10111")
        val hammingDistance = HammingDistance(binaryStrings, 5)

        assertEquals("00010", hammingDistance.getMostDistinctBinaryString())
    }

    @Test
    fun shouldReturnMostUniqueBinaryStringWhenSingletonList(){
        val binaryStrings = listOf("00000000")
        val hammingDistance = HammingDistance(binaryStrings, 8)
        assertEquals("11111111", hammingDistance.getMostDistinctBinaryString())
    }

}