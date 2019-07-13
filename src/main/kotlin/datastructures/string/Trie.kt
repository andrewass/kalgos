package datastructures.string

import entities.TrieNode

/**
 * A class representing a Trie data structure. Composed by connecting multiple [TrieNode]
 */
class Trie {

    private val rootNode = TrieNode('0')

    init {
        rootNode.isEndOfWord = true
    }

    /**
     * Add a word to the trie structure.
     *
     * @param word word to be added to the trie
     */
    fun addWord(word: String) {
        var currentNode = rootNode

        for (letter in word.toLowerCase()) {
            currentNode = currentNode.children
                    .computeIfAbsent(letter) { TrieNode(letter) }
        }
        currentNode.isEndOfWord = true
    }

    /**
     * Searches for a given word in the Trie
     *
     * @param word the word searched for in the Trie
     * @return true if the Trie contains the word, else false
     */
    fun findWord(word : String) : Boolean {
        var currentNode = rootNode

        for (letter in word.toLowerCase()) {
            currentNode = currentNode.children[letter] ?: return false
        }
        return currentNode.isEndOfWord
    }

    /**
     * Find all the words with a common prefix
     *
     * @param prefix word used as prefix
     * @return a list containing all the words with the given prefix
     */
    fun findWordsByPrefix(prefix : String) : List<String> {
        return mutableListOf()
    }

    /**
     * Removes a previously stored word from the Trie
     *
     * @param word word to the removed
     */
    fun removeWord(word: String) {

    }

    private fun remove(node : TrieNode, index : Int, word : String) : Boolean{
        val letter = word[index]
        val childNode = node.children[letter] ?: return false
        val shouldDeleteNode = remove(childNode, index+1, word)
        return false
    }


}