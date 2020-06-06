package algorithms.string

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LongestPalindromeTest {

    @Test
    fun `should return input string as longest palindromic substring `(){
        val word = "saippuakivikauppias"
        val result = longestPalindrome(word)
        assertEquals(word, result)
    }

    @Test
    fun `should return first match when multiple valid results`(){
        val word = "aaaabcdxxxxefyyyy"
        val result = longestPalindrome(word)
        assertEquals("aaaa",result)
    }

    @Test
    fun `should return single character when input is strictly unique characters`(){
        val word = "abcdefghijklmnopqrstuv"
        val result = longestPalindrome(word)
        assertEquals("a", result)
    }

    @Test
    fun `should return empty result when input is empty`(){
        val word = ""
        val result = longestPalindrome(word)
        assertEquals(result,word)
    }

    @Test
    fun `should return single character when input is single character`(){
        val word = "e"
        val result = longestPalindrome(word)
        assertEquals(word, result)
    }
}