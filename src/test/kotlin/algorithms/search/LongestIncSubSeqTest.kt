package algorithms.search

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LongestIncSubSeqTest {

    @Test
    fun shouldFindLongestIncreasingSubsequence() {
        val numbers = listOf(1, 9, 8, 10, 2, 3, 4, 6, 5, 7)
        val lis = longestIncSubSeq(numbers)

        assertEquals(6, lis.size)
        assertEquals(listOf(1,2,3,4,5,7), lis)
    }

    @Test
    fun shouldReturnSingleElementListWhenNoIncreasingSubsequenceExists(){
        val numbers = listOf(13,11,9,8,7,5,4,3,1)
        val lis = longestIncSubSeq(numbers)

        assertEquals(1, lis.size)
        assertEquals(listOf(1), lis)
    }

}