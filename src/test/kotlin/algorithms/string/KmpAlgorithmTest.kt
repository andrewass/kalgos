package algorithms.string

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class KmpAlgorithmTest {

    @Test
    fun shouldReturnListWithCorrectSizeAndIndices() {
        val text = "AABAACAABAABBBAAAABAACAABAABBBBBAABAACAABAA"
        val pattern = "AABAACAABAA"
        val matches = kmpSearch(txt = text, pat = pattern)
        assertEquals(3, matches.size)
        assertEquals(listOf(0, 16, 32), matches)
    }

    @Test
    fun shouldReturnListWithSizeEqualsToText() {
        val text = "AAAAAA"
        val pattern = "A"
        val matches = kmpSearch(txt = text, pat = pattern)
        assertEquals(6, matches.size)
        assertEquals(listOf(0, 1, 2, 3, 4, 5), matches)
    }
}