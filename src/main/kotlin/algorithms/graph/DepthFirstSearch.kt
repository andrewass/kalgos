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

private fun visitChildNodes(currentNode: GraphNode, linkedList: LinkedList<GraphNode>) {
    currentNode.partlyProcessed = true
    for (childNode in currentNode.childrenList) {
        if (isUnprocessed(childNode)) {
            visitChildNodes(childNode, linkedList)
        }
    }
    currentNode.processed = true
    linkedList.addFirst(currentNode)
}

fun <T : GraphNode> isUnprocessed(node: T): Boolean {
    return !node.processed and !node.partlyProcessed
}