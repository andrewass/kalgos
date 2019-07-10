package implementations

import entities.GraphNode

class GraphNodeImpl(val id: Int) :  GraphNode {

    override var partlyProcessed = false

    override var processed = false

    override var childrenList = mutableListOf<GraphNode>()

    override fun addChild(child: GraphNode) {
        childrenList.add(child)
    }

    override fun addChildren(children: List<GraphNode>) {
        for (child in children) {
            childrenList.add(child)
        }
    }
}