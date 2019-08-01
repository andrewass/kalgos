package algorithms.graph

import entities.GraphNode
import java.util.*

/**
 * Performs a depth first search on input graph, represented as a list of nodes.
 * Returns the topological order of the nodes
 *
 * @param nodes representing each node in the graph
 * @return a list representing the nodes in topological order
 */
fun depthFirstSearch(nodes: List<GraphNode>): LinkedList<GraphNode> {
    val linkedList = LinkedList<GraphNode>()
    for (node in nodes) {
        if (isUnprocessed(node)) {
            visitChildNodes(node, linkedList)
        }
    }
    return linkedList
}

/**
 * Process all neighbours of given node recursively. After each neighbours are processed, the node is
 * add to the front of a linked list.
 *
 * @param node the node to be processed
 * @param linkedList the list storing each node
 */
private fun visitChildNodes(node: GraphNode, linkedList: LinkedList<GraphNode>) {
    node.partlyProcessed = true
    for (childNode in node.neighbours) {
        if (isUnprocessed(childNode)) {
            visitChildNodes(childNode, linkedList)
        }
    }
    node.processed = true
    linkedList.addFirst(node)
}

/**
 * Decides if a node is neither partly or fully processed
 *
 * @param node curent node to be examined
 * @return true if node is neither processed or partly processed, else false
 */
fun isUnprocessed(node: GraphNode): Boolean {
    return !node.processed and !node.partlyProcessed
}