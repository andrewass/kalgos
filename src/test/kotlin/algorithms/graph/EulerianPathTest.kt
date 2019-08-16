package algorithms.graph

import entities.GraphEdge
import implementations.GraphNodeImpl


class EulerianPathTest {

    lateinit var nodes: MutableList<GraphNodeImpl>


    fun shouldFindEulerianPathInDirectedConnectedGraph() {
        nodes = mutableListOf<GraphNodeImpl>()

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

        val path = eulerianPathDirGraph(nodes)


    }

    fun addDirectedEdge(from: Int, to: Int) {
        nodes[from].addEdge(GraphEdge(from = nodes[from], to = nodes[to]))
        nodes[to].addReverseEdge(GraphEdge(from = nodes[from], to = nodes[to]))
    }


}