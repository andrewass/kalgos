package entities

interface GraphNode {

    var visited: Boolean

    val id: Int

    var partlyProcessed: Boolean

    var processed: Boolean

    var neighbours: MutableList<GraphNode>

    val edges: MutableList<GraphEdge>

    val reverseEdges: MutableList<GraphEdge>

    var maxDistance: Int

    var previous: GraphNode

    //For use with Strongly Connected Components
    var index: Int
    var lowLink: Int
    var onStack: Boolean

    fun addNeighbour(neighbour: GraphNode)

    fun addNeighbours(neighbours: List<GraphNode>)
}