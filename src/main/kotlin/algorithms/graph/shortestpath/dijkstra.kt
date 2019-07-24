package algorithms.graph.shortestpath

import entities.GraphNode
import java.util.*

/**
 * Find the shortest path from the source node to every other node in the graph,
 * by [Dijkstra's Algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)
 *
 * @param nodes All nodes from the graph
 * @param source The node used as source node
 */
fun dijkstraShortestPath(nodes: List<GraphNode>, source: GraphNode) {
    val queue = TreeSet<GraphNode>(compareBy { it.maxDistance })

    source.maxDistance = 0
    queue.add(source)
    for (node in nodes) {
        if (node != source) {
            node.maxDistance = 1000000000
            queue.add(node)
        }
    }

    while (queue.isNotEmpty()) {
        val current = queue.pollFirst()
        for (edge in current.edges) {
            val to = edge.to
            val alternative = current.maxDistance + edge.weight
            if (alternative < to.maxDistance) {
                to.maxDistance = alternative
                to.previous = current
                queue.remove(to)
                queue.add(to)
            }
        }
    }
}
