package entities

interface GraphNode {

    val id : Int

    var partlyProcessed : Boolean

    var processed : Boolean

    var childrenList : MutableList<GraphNode>

    val edges : MutableList<GraphEdge>

    fun addChild(child : GraphNode)

    fun addChildren(children : List<GraphNode>)
}