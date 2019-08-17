package algorithms.graph

import entities.GraphEdge
import implementations.GraphNodeImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class EulerianPathTest {

    lateinit var nodes: MutableList<GraphNodeImpl>

    @Test
    fun shouldFindEulerianPathInDirectedConnectedGraph() {
        nodes = mutableListOf()
        val edgeCount = 10

        for (i in 0..6) {
            nodes.add(GraphNodeImpl(i))
        }
        addDirectedEdge(0, 1)
        addDirectedEdge(1,2)
        addDirectedEdge(2,3)
        addDirectedEdge(0,4)
        addDirectedEdge(3,0)
        addDirectedEdge(4,3)
        addDirectedEdge(2,6)
        addDirectedEdge(6,5)
        addDirectedEdge(5,2)
        addDirectedEdge(3,5)

        val eulerianPath = EulerianPathDirGraph(nodes, edgeCount)
        val path = eulerianPath.getPath()
        assertEquals(edgeCount+1, path.size)
    }

    @Test
    fun shouldReturnEmptyListWhenNoEulerianPathExistsInDirectedGraph(){
        nodes = mutableListOf()
    }

    fun addDirectedEdge(from: Int, to: Int) {
        nodes[from].addEdge(GraphEdge(from = nodes[from], to = nodes[to]))
        nodes[to].addReverseEdge(GraphEdge(from = nodes[from], to = nodes[to]))
    }
}