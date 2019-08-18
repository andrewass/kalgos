package algorithms.graph

import entities.GraphNode
import java.util.*


/**
 * Finding an [Eulerian Path](https://en.wikipedia.org/wiki/Eulerian_path) of a given graph.
 * Designed for directed edges. Singleton nodes with no incoming or outgoing edges  are allowed
 * in the graph for path to exists, but not two or more distinct connected components containing edges.
 *
 * @param nodes List of the nodes forming the graph
 * @param edgeCount Number of edges in thee graph
 */
class EulerianPathDirGraph(val nodes: List<GraphNode>, val edgeCount: Int) {

    private var startNode: GraphNode? = null
    private var endNode: GraphNode? = null
    private val outEdgeCount = IntArray(nodes.size)
    private val eulerianPath = LinkedList<Int>()


    init {
        if (eulerianPathCanExist()) {
            createPath()
        }
    }

    /**
     * Returns an Eulerian path of nodes
     *
     * @return a list of nodes stored in visisted order
     */
    fun getPath() = eulerianPath

    /**
     * Decides if an actual path can exist by counting in-degrees and out-degrees
     * of each node.
     *
     * @return true if a path can exist, else false
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
     * Find an Eulerian path from the graph by doing a dfs from a selected starting node.
     * If the node count in the path is less than edge count of graph + 1, there exists multiple
     * connected components with edges, hence no path exists.
     */
    private fun createPath() {
        if (startNode == null) {
            startNode = nodes[0]
        }
        dfs(startNode!!)
        if (eulerianPath.size != edgeCount + 1) {
            eulerianPath.clear()
        }
    }

    /**
     * Retrieving the actual path of the graph with recursive dfs calls.
     *
     * @param node Current node
     */
    private fun dfs(node: GraphNode) {
        while (outEdgeCount[node.id] > 0) {
            val nextEdge = node.edges[--outEdgeCount[node.id]]
            dfs(nextEdge.to)
        }
        eulerianPath.addFirst(node.id)
    }
}
