package datastructures.string

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TrieTest {

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
    fun shouldDeletePreviouslyStoredWord(){
        val trie = constructTrie()
        trie.removeWord("Trie")
        trie.removeWord("representing")
        trie.removeWord("a")

        assertFalse(trie.findWord("Trie"))
        assertFalse(trie.findWord("representing"))
        assertFalse(trie.findWord("a"))
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