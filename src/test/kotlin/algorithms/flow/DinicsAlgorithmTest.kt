package algorithms.flow

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DinicsAlgorithmTest {

    private val graph = mutableListOf<FlowNodeImpl>()

    @Test
    fun shouldFindMaxFlowInGraph() {
        fillGraph()
        val maxFlow = dinicMaxFlow(source = graph[0], sink = graph[3], nodes = graph)
        assertEquals(3, maxFlow)
    }

    private fun fillGraph() {
        for (i in 0..3) {
            graph.add(FlowNodeImpl(i))
        }
        addEdge(0, 1, 10L)
        addEdge(1, 2, 1L)
        addEdge(1, 3, 1L)
        addEdge(0, 2, 1L)
        addEdge(2, 3, 10L)
    }

    private fun addEdge(from: Int, to: Int, flow: Long) {
        val edge1 = FlowEdge(to = graph[to], capacity = flow, flowVal = 0L)
        val edge2 = FlowEdge(to = graph[from], capacity = flow, flowVal = flow)
        edge1.reverseEdge = edge2
        edge2.reverseEdge = edge1
        graph[from].edges.add(edge1)
        graph[to].edges.add(edge2)
    }
}