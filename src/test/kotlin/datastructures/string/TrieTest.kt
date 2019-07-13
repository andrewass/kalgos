package datastructures.string

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class   TrieTest {

    @Test
    fun shouldAddWordsToTrieWithoutErrors() {
        constructTrie()
    }

    @Test
    fun shouldFindPreviouslyStoredWords() {
        val trie = constructTrie()
        assertTrue(trie.findWord("Trie"))
        assertTrue(trie.findWord("Words"))
        assertTrue(trie.findWord("representing"))
        assertTrue(trie.findWord("content"))
        assertTrue(trie.findWord("of"))
        assertTrue(trie.findWord("a"))
        assertTrue(trie.findWord("trie"))
    }

    @Test
    fun shouldReturnFalseWhenSearchingForPrefixOfStoredWord() {
        val trie = Trie()
        trie.addWord("programming")
        assertFalse(trie.findWord("program"))
    }

    @Test
    fun shouldReturnFalseWhenSearchingForWordWhereOnlyPrefixIsStored() {
        val trie = Trie()
        trie.addWord("program")
        assertFalse(trie.findWord("programming"))
    }

    @Test
    fun shouldIgnoreCasing() {
        val trie = Trie()
        trie.addWord("Program")
        assertTrue(trie.findWord("program"))
        assertTrue(trie.findWord("Program"))
    }

    @Test
    fun shouldReturnTrueWhenSearchingForEmptyString(){
        val trie = Trie()
        assertTrue(trie.findWord(""))
    }

    @Test
    fun shouldPerformRemovalOfNonInsertedWordsWithoutErrors(){
        val trie = Trie()
        trie.removeWord("program")
    }

    @Test
    fun shouldReturnListOfWordsWithCommonPrefix(){
        val trie = Trie()
        val commonPrefixList = listOf("program","pro","provision","programming")
        commonPrefixList.forEach(trie::addWord)
        trie.addWord("node")
        trie.addWord("random")
        trie.addWord("pr")

        val matches = trie.findWordsByPrefix("pro")
        assertEquals(commonPrefixList.size, matches.size)
        assertEquals(commonPrefixList.sorted(), matches.sorted())
    }

    @Test
    fun shouldDeleteSubsetOfPreviouslyStoredWords(){
        val trie = Trie()
        trie.addWord("program")
        trie.addWord("pro")
        trie.addWord("provision")
        trie.addWord("programming")
        trie.addWord("programmer")
        trie.addWord("programmable")

        trie.removeWord("provision")
        trie.removeWord("program")
        trie.removeWord("pro")
        trie.removeWord("programming")

        assertFalse(trie.findWord("programming"))
        assertFalse(trie.findWord("provision"))
        assertFalse(trie.findWord("pro"))
        assertFalse(trie.findWord("program"))

        assertTrue(trie.findWord("programmer"))
        assertTrue(trie.findWord("programmable"))
    }

    private fun constructTrie(): Trie {
        val trie = Trie()
        trie.addWord("Trie")
        trie.addWord("Words")
        trie.addWord("representing")
        trie.addWord("content")
        trie.addWord("of")
        trie.addWord("a")
        trie.addWord("trie")
        return trie
    }
}