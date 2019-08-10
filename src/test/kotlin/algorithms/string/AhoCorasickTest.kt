package algorithms.string

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AhoCorasickTest {

    @Test
    fun shouldFindCorrectPatternMatches() {
        val text = "This is a pretty random text which should contain matches, " +
                "when matched against a set of patterns. Matches are stored by " +
                "the index in the text which is also the first index of the pattern."

        val patterns = listOf("Match", "match", "pat", "pattern", "patterns", ".", "a", "missing")

        val ahoCorasick = AhoCorasick(text, patterns)
        val matches = ahoCorasick.getMatches()

        assertEquals(1, matches[0].size)
        assertEquals(2, matches[1].size)
        assertEquals(2, matches[2].size)
        assertEquals(2, matches[3].size)
        assertEquals(1, matches[4].size)
        assertEquals(2, matches[5].size)
        assertEquals(13, matches[6].size)
        assertEquals(0, matches[7].size)
    }

    @Test
    fun shouldHandleDuplicatePatterns() {
        val text = "abcKAbcxyzXXXXxyzTKJRHabc"

        val patterns = listOf("abc", "xyz", "abc", "xyz")

        val ahoCorasick = AhoCorasick(text, patterns)
        val matches = ahoCorasick.getMatches()

        assertEquals(2, matches[0].size)
        assertEquals(2, matches[1].size)
        assertEquals(listOf(0, 22), matches[0])
        assertEquals(listOf(7, 14), matches[1])
        assertEquals(matches[0], matches[2])
        assertEquals(matches[1], matches[3])
    }

    @Test
    fun shouldFindCorrectMatchesWhenAllCharactersAreIdentical() {
        val text = "aaaaaaaaaaaaaaaaa"

        val patterns = listOf("a", "aaa", "aaaaa")

        val ahoCorasick = AhoCorasick(text, patterns)
        val matches = ahoCorasick.getMatches()

        assertEquals((0..16).toList(), matches[0])
        assertEquals((0..14).toList(), matches[1])
        assertEquals((0..12).toList(), matches[2])
    }
}