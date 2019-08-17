package algorithms.graph

import entities.GraphNode
import java.util.*


class EulerianPathDirGraph(val nodes: List<GraphNode>, val edgecount : Int) {

    private var startNode: GraphNode? = null
    private var endNode: GraphNode? = null
    private val outEdgeCount = IntArray(nodes.size)
    private val eulerianPath = LinkedList<Int>()


    init {
        if (eulerianPathCanExist()) {
            createPath()
        }
    }

    fun getPath() = eulerianPath

    /**
     *
     */
    private fun eulerianPathCanExist(): Boolean {
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

        return alt1 || alt2 || alt3
    }

    /**
     *
     */
    private fun createPath() {
        if (startNode == null) {
            startNode = nodes[0]
        }
        dfs(startNode!!)
        if(eulerianPath.size != edgecount+1){
            eulerianPath.clear()
        }
    }

    private fun dfs(node: GraphNode) {
        while (outEdgeCount[node.id] > 0) {
            val nextEdge = node.edges[--outEdgeCount[node.id]]
            dfs(nextEdge.to)
        }
        eulerianPath.addFirst(node.id)
    }
}