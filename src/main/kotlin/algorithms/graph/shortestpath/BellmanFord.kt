package algorithms.graph.shortestpath

import entities.GraphEdge
import entities.GraphNode
import java.util.*

/**
 * Implementation of [Bellman–Ford algorithm](https://en.wikipedia.org/wiki/Bellman%E2%80%93Ford_algorithm).
 * Find the shortest path from a given source to every other vertex in the graph. If a vertex is not reachable from source
 * distance is set to [Int.MAX_VALUE]. If a negative cycle exists on path between the source and the vertex, the vertex is marked
 * as cycle in the response array in [BellmanFordResult]
 *
 * @param vertices List of the vertices of the graph
 * @param source Index of the source vertex
 * @param edges List of the edges of the graph
 * @return a [BellmanFordResult] containing arrays of distances and cycles
 */
fun bellmanFordShortestPath(vertices: List<GraphNode>, source: Int, edges: List<GraphEdge>): BellmanFordResult {
    val distance = IntArray(vertices.size) { Int.MAX_VALUE }
    val cycles = BooleanArray(vertices.size)
    distance[source] = 0

    for (i in 1 until vertices.size) {
        for (edge in edges) {
            if (distance[edge.from.id] != Int.MAX_VALUE) {
                if (distance[edge.from.id] + edge.weight < distance[edge.to.id]) {
                    distance[edge.to.id] = distance[edge.from.id] + edge.weight
                }
            }
        }
    }

    for (edge in edges) {
        if (distance[edge.from.id] != Int.MAX_VALUE) {
            if (distance[edge.from.id] + edge.weight < distance[edge.to.id]) {
                if (!cycles[edge.to.id]) {
                    spreadCycleMark(edge.to, cycles)
                }
            }
        }
    }
    return BellmanFordResult(distance, cycles)
}

/**
 * Process a node known to belong in a negative cycle.
 * Marks all other reachable nodes as cycle nodes as well.
 * Visits nodes using a Breadth First Search
 *
 * @param node A node which is part of a negative cycle
 * @param cycles A boolean array used for marking cycles by vertex index
 */
fun spreadCycleMark(node: GraphNode, cycles: BooleanArray) {
    cycles[node.id] = true
    val queue = LinkedList(listOf(node))

    while (queue.isNotEmpty()){
        val current = queue.removeFirst()
        for(edge in current.edges){
            if(!cycles[edge.to.id]){
                cycles[edge.to.id] = true
                queue.addLast(edge.to)
            }
        }
    }
}