package implementations

import entities.GraphEdge
import entities.GraphNode

class GraphNodeImpl(override val id: Int) :  GraphNode {

    override var previous: GraphNode = this

    override var maxDistance: Int = 0

    override var partlyProcessed = false

    override var processed = false

    override var index = -1

    override var lowLink = 0

    override var onStack = false

    override var neighbours = mutableListOf<GraphNode>()

    override val edges = mutableListOf<GraphEdge>()

    override fun addNeighbour(child: GraphNode) {
        neighbours.add(child)
    }

    override fun addNeighbours(children: List<GraphNode>) {
        for (child in children) {
            this.neighbours.add(child)
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