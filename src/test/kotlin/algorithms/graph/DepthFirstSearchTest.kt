package algorithms.graph

import implementations.GraphNodeImpl
import org.junit.jupiter.api.Test
import utils.createDirectedAcyclicGraph
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DepthFirstSearchTest {

    @Test
    fun shouldReturnListWithNodesInTopologicalOrder() {
        val dagGraph = createDirectedAcyclicGraph()
        val topologicalOrderList = depthFirstSearch(dagGraph)

        assertEquals(topologicalOrderList.size, dagGraph.size)
        assertEquals(topologicalOrderList[0], dagGraph[6])
        assertEquals(topologicalOrderList[1], dagGraph[0])
        assertEquals(topologicalOrderList[2], dagGraph[2])
        assertEquals(topologicalOrderList[3], dagGraph[1])
        assertEquals(topologicalOrderList[4], dagGraph[4])
        assertEquals(topologicalOrderList[5], dagGraph[3])
        assertEquals(topologicalOrderList[6], dagGraph[5])
    }

    @Test
    fun shouldReturnEmptyListWhenInputGraphContainsNoNodes() {
        val emptyGraph = emptyList<GraphNodeImpl>()
        val topologicalOrderList = depthFirstSearch(emptyGraph)

        assertTrue(topologicalOrderList.isEmpty())
    }
}