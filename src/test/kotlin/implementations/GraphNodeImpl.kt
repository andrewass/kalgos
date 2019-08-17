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

    override var visited = false

    override var neighbours = mutableListOf<GraphNode>()

    override val edges = mutableListOf<GraphEdge>()

    override val reverseEdges = mutableListOf<GraphEdge>()

    override fun addNeighbour(neighbour: GraphNode) {
        neighbours.add(neighbour)
    }

    override fun addNeighbours(neighbours: List<GraphNode>) {
        for (child in neighbours) {
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

    fun addReverseEdge(edge : GraphEdge){
        reverseEdges.add(edge)
    }
}