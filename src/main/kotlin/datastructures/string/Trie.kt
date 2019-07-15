package datastructures.string

import java.lang.StringBuilder

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
        val wordLowerCase = word.toLowerCase()

        for (letter in wordLowerCase) {
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
    fun findWord(word: String): Boolean {
        val wordLowerCase = word.toLowerCase()
        var currentNode = rootNode

        for (letter in wordLowerCase) {
            currentNode = currentNode.children[letter] ?: return false
        }
        return currentNode.isEndOfWord
    }

    /**
     * Find all the words with a common word
     *
     * @param word word used as word
     * @return a list containing all the words with the given word
     */
    fun findWordsByPrefix(word: String): List<String> {
        val prefix = word.toLowerCase()
        val matches = mutableListOf<String>()
        var currentNode = rootNode
        for (letter in prefix) {
            currentNode = currentNode.children[letter] ?: return matches
        }
        if(currentNode.isEndOfWord){
            matches.add(word)
        }
        for (child in currentNode.children.values) {
            constructCommonPrefixWords(child, StringBuilder(word), matches)
        }
        return matches
    }

    /**
     * Construct words with a common prefix . Words are built with [StringBuilder], and added to a list when complete.
     *
     * @param node Current node in the Trie
     * @param word Current prefix of a word
     * @param matches List storing all the words with a given common prefix
     */
    private fun constructCommonPrefixWords(node: TrieNode, word: StringBuilder, matches: MutableList<String>) {
        val wordBuilder = word.append(node.letter)
        if (node.isEndOfWord) {
            matches.add(wordBuilder.toString())
        }
        for (child in node.children.values) {
            constructCommonPrefixWords(child, wordBuilder, matches)
        }
    }

    /**
     * Removes a previously stored word from the Trie
     *
     * @param word word to the removed
     */
    fun removeWord(word: String) {
        remove(rootNode, 0, word.toLowerCase())
    }

    /**
     * Recursively deciding whether we can remove a child node for a given node
     *
     * @param node current node
     * @param index current index of the word to be removed
     * @param word word to be removed from the Trie
     * @return true if a given child node may be removed, else false
     */
    private fun remove(node: TrieNode, index: Int, word: String): Boolean {
        if (index == word.length) {
            return when {
                !node.isEndOfWord -> false
                else -> {
                    node.isEndOfWord = false
                    node.children.isEmpty()
                }
            }
        }
        val childNode = node.children[word[index]] ?: return false
        val canDeleteChild = remove(childNode, index + 1, word) && !childNode.isEndOfWord
        return when {
            canDeleteChild -> {
                node.children.remove(word[index])
                node.children.isEmpty()
            }
            else -> false
        }
    }
}