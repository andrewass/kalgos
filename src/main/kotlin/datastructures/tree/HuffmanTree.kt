package datastructures.tree

import java.util.*

/**
 * Perform Huffman Coding on a given String by constructing a Huffman Tree
 */
class HuffmanTree(private val word: String) {

    private val priorityQueue = PriorityQueue<Node>()
    private var rootNode: Node? = null
    private val encodeMapper = hashMapOf<Char, String>()
    private var encodingLength = 0

    init {
        val charFrequencyMap = word.groupBy { it }
        charFrequencyMap.forEach { (t, u) -> priorityQueue.add(Node(t, u.size)) }
        buildTree()
        encodeWord(rootNode, StringBuilder(""))
    }

    fun getEncoding(): BitSet {
        val bitSet = BitSet(encodingLength)
        var index = 0
        word.forEach {
            val encode = encodeMapper[it]
            encode!!.forEach { char ->
                if (char == '1') {
                    bitSet.set(index)
                }
                index++
            }
        }
        return bitSet
    }

    fun getDecoding(bitSet: BitSet): String {
        val decoding = StringBuilder("")
        var node = rootNode

        for (i in 0 until bitSet.length()) {
            node = if (!bitSet.get(i)) {
                node!!.left
            } else {
                node!!.right
            }
            if (node!!.char != null) {
                decoding.append(node.char)
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
                encodingLength += node.freq * code.length
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