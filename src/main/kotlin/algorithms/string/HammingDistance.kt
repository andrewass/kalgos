package algorithms.string

import utils.binaryStringToInt
import utils.toBinaryString
import java.util.*
import kotlin.math.pow

/**
 * Class for computing the least similar binary string of equal length, compared to any of the
 * binary strings in the input list.
 *
 * @param binaryStrings List of binary strings compared against
 * @param len Length of each binary string in the list
 */
class HammingDistance(private val binaryStrings: List<String>, private val len: Int) {

    private val n = 2.00.pow(len).toInt()
    val nodes = mutableListOf<Node>()

    init {
        createGraph()
        bfsToSetHammingDistance()
    }


    fun getMostDistinctBinaryString(): String {
        val node = nodes.stream()
                .max(Comparator.comparing(Node::distance)).get()
        return node.value.toBinaryString(len)
    }

    private fun createGraph() {
        for (i in 0 until n) {
            nodes.add(Node(i))
        }
    }

    private fun bfsToSetHammingDistance() {
        val queue = initializeQueue()
        while (queue.isNotEmpty()) {
            val current = queue.pollFirst()
            visitNeighbours(current.value, queue, current.distance + 1)
        }
    }

    private fun visitNeighbours(nodeValue: Int, queue: LinkedList<Node>, distance: Int) {
        var shiftVal = 1
        for (i in 1..len) {
            val currentValue = nodeValue xor shiftVal
            val current = nodes[currentValue]
            if (current.distance > distance) {
                current.distance = distance
                queue.addLast(current)
            }
            shiftVal = 1 shl i
        }
    }

    private fun initializeQueue(): LinkedList<Node> {
        val queue = LinkedList<Node>()
        binaryStrings.forEach {
            val number = it.binaryStringToInt()
            nodes[number].distance = 0
            queue.addLast(nodes[number])
        }
        return queue
    }

    inner class Node(val value: Int) {
        var distance = len
    }
}

