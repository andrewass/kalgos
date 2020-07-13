package datastructures.tree

import java.util.*

/**
 * Perform Huffman Coding on a given String by constructing a Huffman Tree
 */
class HuffmanTree(private val word: String) {

    private val priorityQueue = PriorityQueue<Node>()
    private var rootNode: Node? = null
    private val encodeMapper = hashMapOf<Char, String>()

    init {
        val charFrequencyMap = word.groupBy { it }
        charFrequencyMap.forEach { (t, u) -> priorityQueue.add(Node(t, u.size)) }
        buildTree()
        encodeWord(rootNode, StringBuilder(""))
    }

    fun getEncoding(): String {
        val encoding = StringBuilder("")
        word.forEach {
            encoding.append(encodeMapper[it])
        }
        return encoding.toString()
    }

    fun getDecoding(encoding: String): String {
        val decoding = StringBuilder("")
        var node = rootNode

        encoding.forEach {
            node = if (it == '0') {
                node!!.left
            } else {
                node!!.right
            }
            if (node!!.char != null) {
                decoding.append(node!!.char)
                node = rootNode
            }
        }
        return decoding.toString()
    }


    private fun buildTree() {
        if (word.length == 1) {
            priorityQueue.add(Node(freq = 0))
        }
        while (priorityQueue.size > 1) {
            val firstNode = priorityQueue.poll()
            val secondNode = priorityQueue.poll()
            val newNode = Node(freq = firstNode.freq + secondNode.freq)
            newNode.left = firstNode
            newNode.right = secondNode
            priorityQueue.add(newNode)
        }
        rootNode = priorityQueue.poll()
    }

    private fun encodeWord(node: Node?, code: StringBuilder) {
        if (node != null) {
            if (node.char != null) {
                encodeMapper[node.char] = code.toString()
            } else {
                encodeWord(node.left, code.append("0"))
                code.setLength(code.length - 1)
                encodeWord(node.right, code.append("1"))
                code.setLength(code.length - 1)
            }
        }
    }

    inner class Node(val char: Char? = null, val freq: Int) : Comparable<Node> {
        var left: Node? = null
        var right: Node? = null

        override fun compareTo(other: Node): Int {
            return this.freq - other.freq
        }
    }
}