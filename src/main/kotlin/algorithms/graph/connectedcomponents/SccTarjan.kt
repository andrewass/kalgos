package algorithms.graph.connectedcomponents

import entities.GraphNode
import java.lang.Integer.min
import java.util.*

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

    fun getStronglyConnectedComponents() = components

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
            while (stack.isNotEmpty()) {
                val curr = stack.pollFirst()
                curr.onStack = false
                component.nodes.add(curr)
            }
            components.add(component)
        }
    }
}