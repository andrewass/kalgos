package implementations

import entities.GraphEdge
import entities.GraphNode

class GraphNodeImpl(override val id: Int) :  GraphNode {

    override var previous: GraphNode = this

    override var maxDistance: Int = 0

    override var partlyProcessed = false

    override var processed = false

    override var children = mutableListOf<GraphNode>()

    override val edges = mutableListOf<GraphEdge>()

    override fun addChild(child: GraphNode) {
        children.add(child)
    }

    override fun addChildren(children: List<GraphNode>) {
        for (child in children) {
            this.children.add(child)
        }
    }

    fun addEdge(edge: GraphEdge) {
        edges.add(edge)
    }

    fun addEdges(edges: List<GraphEdge>) {
        for (edge in edges) {
            this.edges.add(edge)
        }
    }
}