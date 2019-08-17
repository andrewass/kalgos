package algorithms.graph

import entities.GraphNode
import java.util.*


class EulerianPathDirGraph(val graph: List<GraphNode>, val edgecount : Int) {

    private var startNode: GraphNode? = null
    private var endNode: GraphNode? = null
    private var componentSize = 0
    private val outEdgeCount = IntArray(graph.size)
    private val eulerianPath = LinkedList<Int>()

    private val singleNodes = graph.asSequence()
            .filter { it.edges.isEmpty() && it.reverseEdges.isEmpty() }
            .toList()

    private val nodes = graph.asSequence()
            .filter { it.edges.isNotEmpty() || it.reverseEdges.isNotEmpty() }
            .toList()

    init {
        if (eulerianPathExists()) {
            println("Path exists")
            createPath()
        }
    }

    fun getPath() = eulerianPath

    /**
     *
     */
    private fun eulerianPathExists(): Boolean {
        var eqDegreeNodes = 0
        for (node in nodes) {
            when {
                node.edges.size == node.reverseEdges.size -> eqDegreeNodes++
                node.edges.size - node.reverseEdges.size == 1 -> startNode = node
                node.reverseEdges.size - node.edges.size == 1 -> endNode = node
            }
            outEdgeCount[node.id] = node.edges.size
        }
        val alt1 = eqDegreeNodes == nodes.size
        val alt2 = eqDegreeNodes == nodes.size - 1 && (startNode != null || endNode != null)
        val alt3 = eqDegreeNodes == nodes.size - 2 && startNode != null && endNode != null
        checkConnectivity(nodes[0])
        return (alt1 || alt2 || alt3) && componentSize == nodes.size
    }

    /**
     *
     */
    private fun checkConnectivity(node: GraphNode) {
        if (node.visited) {
            return
        }
        componentSize++
        node.visited = true
        for (edge in node.edges) {
            checkConnectivity(edge.to)
        }
        for (edge in node.reverseEdges) {
            checkConnectivity(edge.to)
        }
    }

    /**
     *
     */
    private fun createPath() {
        if (startNode == null) {
            startNode = nodes[0]
        }
        dfs(startNode!!)
    }


    private fun dfs(node: GraphNode) {
        while (outEdgeCount[node.id] > 0) {
            val nextEdge = node.edges[--outEdgeCount[node.id]]
            dfs(nextEdge.to)
        }
        eulerianPath.addFirst(node.id)
    }
}