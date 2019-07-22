package datastructures.string

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SuffixArrayTest {

    @Test
    fun shouldFindLongestCommonSubstringInASingleWord() {
        val suffixArray = SuffixArray("bananahavana")
        suffixArray.constructLcpArray()
        val longestSubstrings: Set<String> = suffixArray.getLongestCommonSubstrings()

        assertEquals(3, suffixArray.getLongestCommonSubstringLength())
        assertEquals(1, longestSubstrings.size)
        assertEquals("ana", longestSubstrings.first())
        assertEquals(11, suffixArray.at(0))
        assertEquals(8, suffixArray.at(11))
    }

    @Test
    fun shouldFindLongestCommonSubstringInWordWithAllUniqueCharacters() {
        val suffixArray = SuffixArray("abcdefgh")
        suffixArray.constructLcpArray()
        val longestSubstrings: Set<String> = suffixArray.getLongestCommonSubstrings()

        assertEquals(0, suffixArray.getLongestCommonSubstringLength())
        assertEquals(0, longestSubstrings.size)
    }

    @Test
    fun shouldFindLongestCommonSubstringInWordWithOneUniqueCharacter() {
        val suffixArray = SuffixArray("aaaaaaaa")
        suffixArray.constructLcpArray()
        val longestSubstrings = suffixArray.getLongestCommonSubstrings()

        assertEquals(7, suffixArray.getLongestCommonSubstringLength())
        assertEquals(1, longestSubstrings.size)
        assertEquals("aaaaaaa", longestSubstrings.first())
    }
}