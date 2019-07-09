package entities

class GraphNode(val id : Int) : Node {

    override var startTime: Long = 0L

    override val finishTime: Long = 0L

    override var visited: Boolean = false

    override var child: List<Node> = mutableListOf<GraphNode>()

    override var parent: Node? = null
}