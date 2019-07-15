package algorithms.graph.shortestpath

import entities.GraphEdge
import implementations.GraphNodeImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BellmanFordTest {

    private val nodes = mutableListOf<GraphNodeImpl>()
    private val edges = mutableListOf<GraphEdge>()

    @Test
    fun shouldFindShortestPathWhenReachable() {
        addNodesAndEdges()
        val result = bellmanFordShortestPath(nodes, 0, edges)

        assertEquals(0, result.distance[0])
        assertFalse(result.cycles[5])

        assertEquals(8, result.distance[5])
        assertFalse(result.cycles[5])

        assertEquals(5, result.distance[6])
        assertFalse(result.cycles[6])

        assertEquals(Int.MAX_VALUE, result.distance[4])
        assertFalse(result.cycles[4])

        assertTrue(result.cycles[1])
        assertTrue(result.cycles[2])
    }

    private fun addNodesAndEdges() {
        for (i in 0..6) {
            nodes.add(GraphNodeImpl(i))
        }
        addEdge(0, 1, 99)
        addEdge(1, 2, -2)
        addEdge(2, 1, 1)
        addEdge(0, 3, 2)
        addEdge(3, 6, 3)
        addEdge(6, 5, 3)
        addEdge(3, 5, 8)
    }

    private fun addEdge(from: Int, to: Int, weight: Int) {
        val edge = GraphEdge(nodes[from], nodes[to], weight)
        edges.add(edge)
        nodes[from].edges.add(edge)
    }
}