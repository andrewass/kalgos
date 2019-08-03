package algorithms.graph.connectedcomponents

import entities.GraphNode
import java.lang.Integer.min
import java.util.*

/**
 * Implementation of
 * [Tarjan's algorithm](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm)
 * for finding the strongly connected components of a graph
 *
 * @param nodes The graph represented as list of nodes
 */
class SccTarjan(nodes: List<GraphNode>) {

    private var index = 0
    private val stack = LinkedList<GraphNode>()
    private val components = mutableListOf<ConnectedComponent>()

    init {
        for (node in nodes) {
            if (node.index == -1) {
                strongConnect(node)
            }
        }
    }

    /**
     * Get the list of strongly connected components
     *
     * @return the list of strongly connected components
     */
    fun getStronglyConnectedComponents() = components

    /**
     * Identifies the strongly connected components by a DFS and the use of a stack
     *
     * @param node Current node
     */
    private fun strongConnect(node: GraphNode) {
        node.index = index
        node.lowLink = index
        index++
        stack.addFirst(node)
        node.onStack = true

        for (neighbour in node.neighbours) {
            if (neighbour.index == -1) {
                strongConnect(neighbour)
                node.lowLink = min(node.lowLink, neighbour.lowLink)
            } else if (neighbour.onStack) {
                node.lowLink = min(node.lowLink, neighbour.index)
            }
        }

        //If current node is root of a SCC, gather all nodes of the component
        if (node.lowLink == node.index) {
            val component = ConnectedComponent()
            do {
                val curr = stack.pollFirst()
                curr.onStack = false
                component.nodes.add(curr)
            } while (curr != node)
            components.add(component)
        }
    }
}