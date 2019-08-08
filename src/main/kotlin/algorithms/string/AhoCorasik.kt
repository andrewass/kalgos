package algorithms.string

import java.util.*

class AhoCorasick(private val word: String, private val patterns: List<String>) {

    val alphaSize = 26
    private val trie = mutableListOf<Vertex>()
    private val matches = Array(patterns.size) { mutableListOf<Int>() }

    init {
        trie.add(Vertex(0))
        for (i in patterns.indices) {
            addPattern(patterns[i], i)
        }
        processSuffixLinks()
        findMatches()
        printMatches()
    }

    /**
     *
     */
    private fun addPattern(word: String, patIndex: Int) {
        var curr = 0
        for (char in word) {
            val index = char - 'a'
            if (trie[curr].next[index] == -1) {
                trie[curr].next[index] = trie.size
                trie.add(Vertex(trie.size, curr, char))
            }
            curr = trie[curr].next[index]
        }
        trie[curr].leaf = true
        trie[curr].patIndex = patIndex
    }

    private fun processSuffixLinks() {
        val queue = LinkedList<Int>()
        queue.addFirst(0)

        while (queue.isNotEmpty()){
            val curr = queue.pollFirst()
            addSuffixLink(curr)
            for(i in trie[curr].next.indices){
                if(trie[curr].next[i] != -1){
                    queue.addLast(trie[curr].next[i])
                }
            }
        }
    }

    /**
     *
     */
    private fun findMatches() {

        var position = 0
        var ind = 0
        for (char in word) {
            ind = go(ind, char)
            if (trie[ind].leaf) {
                //println("***** FOUND a match at position $position with char $char")
                val longestMatch = patterns[trie[ind].patIndex].length
                val startPosition = position - longestMatch + 1
                addMatch(trie[ind], startPosition, longestMatch)
            }
            position++
        }
    }

    private fun addMatch(curr: Vertex, startPosition: Int, longestMatch: Int) : Int {
        if (curr.leaf) {
            //println("       Found a match for ${patterns[curr.patIndex]}")
            matches[curr.patIndex].add( startPosition + (longestMatch - patterns[curr.patIndex].length))
        }
        when {
            curr.index == 0 -> return 0
            curr.matchLink == -1 -> curr.matchLink = addMatch(trie[curr.link], startPosition, longestMatch)
            else -> curr.matchLink = addMatch(trie[curr.matchLink], startPosition, longestMatch)
        }
        return when {
            curr.leaf -> curr.index
            else -> curr.matchLink
        }
    }

    /**
     * Returns the transition vertex at given vertex on a transition character
     */
    private fun go(ind: Int, letter: Char): Int {
        val c: Int = letter - 'a'
        //If no transition is set for current node to next letter
        if (trie[ind].go[c] == -1) {
            //And there exists an edge in the trie, use this edge as the transition
            if (trie[ind].next[c] != -1) {
                trie[ind].go[c] = trie[ind].next[c]
            } else {
                trie[ind].go[c] = if (ind == 0) 0 else go(addSuffixLink(ind), letter)
            }
        }
        return trie[ind].go[c]
    }

    private fun addSuffixLink(ind: Int): Int {
        if (trie[ind].link == -1) {
            if (ind == 0 || trie[ind].p == 0) {
                trie[ind].link = 0
            } else {
                trie[ind].link = go(addSuffixLink(trie[ind].p), trie[ind].pChar)
            }
        }
        return trie[ind].link
    }

    private fun printMatches() {
        for (i in matches.indices) {
            print("Pattern ${patterns[i]} matches : ")
            for (match in matches[i]) {
                print("$match ")
            }
            println()
        }
    }


    inner class Vertex(val index : Int, val p: Int = -1, val pChar: Char = Char.MIN_VALUE) {
        val next = IntArray(alphaSize) { -1 }
        val go = IntArray(alphaSize) { -1 }
        var leaf = false
        var patIndex = -1
        var link = -1
        var matchLink = -1
    }
}