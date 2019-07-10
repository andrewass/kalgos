package entities

interface GraphNode {
    var partlyProcessed : Boolean

    var processed : Boolean

    var childrenList : MutableList<GraphNode>

    fun addChild(child : GraphNode)

    fun addChildren(children : List<GraphNode>)
}