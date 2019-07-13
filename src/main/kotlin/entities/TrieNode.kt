package entities

class TrieNode(val letter: Char) {

    val children = mutableMapOf<Char, TrieNode>()
    var isEndOfWord : Boolean = false
}