package algorithms.flow

import java.lang.Long.min
import java.util.*

/**
 * Using [Dinic's algorithm](https://en.wikipedia.org/wiki/Dinic%27s_algorithm) to find max flow
 * of a graph
 *
 * @param source Node used as source
 * @param sink Node used as sink
 * @param nodes List of nodes in the graph
 * @return the maximum flow of the graph
 */
fun dinicMaxFlow(source: FlowNode, sink: FlowNode, nodes: List<FlowNode>): Long {
    var maxFlow: Long = 0
    while (constructLevelGraph(source, sink, nodes)) {
        var flow: Long
        do {
            flow = sendFlow(source, sink, Long.MAX_VALUE)
            maxFlow += flow
        } while (flow > 0)
    }
    return maxFlow
}

/**
 * Finding blocking flow using a Depth First Search
 *
 * @param node Current node visited in the graph
 * @sink sink Node used as sink
 * @flow Current flow
 * @return the amount flow possible to pass through an edge from current node
 */
private fun sendFlow(node: FlowNode, sink: FlowNode, flow: Long): Long {
    if (node == sink) {
        return flow
    }
    for (edge in node.edges) {
        if (edge.to.level == node.level + 1 && edge.capacity > edge.flow) {
            val minFlow = min(flow, edge.capacity - edge.flow)
            val resultFlow = sendFlow(edge.to, sink, minFlow)
            if (resultFlow > 0) {
                edge.flow += resultFlow
                edge.reverseEdge.flow -= resultFlow
                return resultFlow
            }
        }
    }
    return 0
}

/**
 * Performing a Breadth First Search to construct the level graph
 *
 * @param source Node used as source
 * @param sink Node used as sink
 * @param nodes List of nodes the graph
 * @return true if sink node is reachable from the level graph, else false
 */
private fun constructLevelGraph(source: FlowNode, sink: FlowNode, nodes: List<FlowNode>): Boolean {
    for (node in nodes) {
        node.level = -1
    }
    source.level = 0
    val queue = LinkedList(listOf(source))
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        for (edge in node.edges) {
            if (edge.flow < edge.capacity && edge.to.level == -1) {
                edge.to.level = node.level + 1
                queue.addLast(edge.to)
            }
        }
    }
    return sink.level > 0
}
