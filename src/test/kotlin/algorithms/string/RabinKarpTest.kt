package algorithms.string

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RabinKarpTest {

    @Test
    fun shouldReturnCorrectPatternMatches() {
        val text = "AABAACAABAAB BBAAAABAACAABAABBBBBAABAACAABAA"
        val pattern = "AABAACAABAA"
        val matches = rabinKarp(word = text, pattern = pattern)
        assertEquals(3, matches.size)
        assertEquals(listOf(0, 17, 33), matches)
    }

    @Test
    fun shouldReturnListWithSizeEqualsToText() {
        val text = "AAAAAA"
        val pattern = "A"
        val matches = rabinKarp(word = text, pattern = pattern)
        assertEquals(6, matches.size)
        assertEquals(listOf(0, 1, 2, 3, 4, 5), matches)
    }

    @Test
    fun shouldReturnEmptyListWhenThereIsNoMatch() {
        val text = "abcdefghijklmnop"
        val pattern = "q"
        val matches = rabinKarp(word = text, pattern = pattern)
        assertEquals(0, matches.size)
    }

    @Test
    fun shouldReturnCorrectResultsWhenOverlappingSubstring() {
        val text = "AAAAAAAAAA"
        val pattern = "AAA"
        val matches = rabinKarp(word = text, pattern = pattern)
        assertEquals(8, matches.size)
        assertEquals(listOf(0,1,2,3,4,5,6,7), matches)
    }

}