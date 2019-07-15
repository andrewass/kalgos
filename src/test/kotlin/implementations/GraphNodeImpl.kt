package implementations

import entities.GraphEdge
import entities.GraphNode

class GraphNodeImpl(override val id: Int) :  GraphNode {

    override var partlyProcessed = false

    override var processed = false

    override var childrenList = mutableListOf<GraphNode>()

    override val edges = mutableListOf<GraphEdge>()

    override fun addChild(child: GraphNode) {
        childrenList.add(child)
    }

    override fun addChildren(children: List<GraphNode>) {
        for (child in children) {
            childrenList.add(child)
        }
    }
}