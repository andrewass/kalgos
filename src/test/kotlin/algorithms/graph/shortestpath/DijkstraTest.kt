package algorithms.graph.shortestpath

import entities.GraphEdge
import implementations.GraphNodeImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DijkstraTest {

    @Test
    fun shouldFindCorrectShortestPathFromSource() {
        val n = 8
        val nodes = mutableListOf<GraphNodeImpl>()

        for (i in 0..n) {
            nodes.add(GraphNodeImpl(i))
        }
        nodes[0].addEdges(listOf(GraphEdge(nodes[0], nodes[1], 3)))

        nodes[1].addEdges(listOf(GraphEdge(nodes[1], nodes[2], 5)))
        nodes[1].addEdges(listOf(GraphEdge(nodes[1], nodes[3], 4)))

        nodes[2].addEdges(listOf(GraphEdge(nodes[2], nodes[3], 6)))
        nodes[2].addEdges(listOf(GraphEdge(nodes[2], nodes[4], 7)))

        nodes[3].addEdges(listOf(GraphEdge(nodes[3], nodes[4], 8)))
        nodes[3].addEdges(listOf(GraphEdge(nodes[3], nodes[5], 6)))
        nodes[3].addEdges(listOf(GraphEdge(nodes[3], nodes[6], 2)))

        nodes[4].addEdges(listOf(GraphEdge(nodes[4], nodes[7], 1)))

        nodes[5].addEdges(listOf(GraphEdge(nodes[5], nodes[4], 5)))

        nodes[6].addEdges(listOf(GraphEdge(nodes[6], nodes[5], 2)))
        nodes[6].addEdges(listOf(GraphEdge(nodes[6], nodes[7], 1)))

        dijkstraShortestPath(nodes, nodes[0])

        assertEquals(0, nodes[0].maxDistance)
        assertEquals(3, nodes[1].maxDistance)
        assertEquals(8, nodes[2].maxDistance)
        assertEquals(7, nodes[3].maxDistance)
        assertEquals(15, nodes[4].maxDistance)
        assertEquals(11, nodes[5].maxDistance)
        assertEquals(9, nodes[6].maxDistance)
        assertEquals(10, nodes[7].maxDistance)
        assertEquals(1000000000, nodes[8].maxDistance)
    }

}