package algorithms.gametheory

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class NimTest {

    @Test
    fun shouldReturnCorrectResultForGivenSetOfValidRemovesCase1() {
        val removes = listOf(2, 5)

        assertEquals("L", decideNimWinner(listOf(5, 12), removes, Array(13) { -1 }, 2))
        assertEquals("W", decideNimWinner(listOf(2, 4, 7), removes, Array(8) { -1 }, 2))
        assertEquals("W", decideNimWinner(listOf(2, 3, 7, 12), removes, Array(13) { -1 }, 2))
    }

    @Test
    fun shouldReturnCorrectResultForGivenSetOfValidRemovesCase2() {
        val removes = listOf(1, 2, 3, 4, 5)

        assertEquals("W", decideNimWinner(listOf(5, 12), removes, Array(13) { -1 }, 1))
        assertEquals("W", decideNimWinner(listOf(2, 4, 7), removes, Array(8) { -1 }, 1))
        assertEquals("L", decideNimWinner(listOf(2, 3, 7, 12), removes, Array(13) { -1 }, 1))
    }

    @Test
    fun shouldReturnCorrectResultWhenEveryRemovalIsValid(){
        assertEquals("L", decideNimWinner(heaps = listOf(4,4,2,2,6,6)))
        assertEquals("W", decideNimWinner(heaps = listOf(4,4,2,2,6,5)))
    }
}