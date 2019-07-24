package entities

import com.sun.corba.se.impl.orbutil.graph.Graph

interface GraphNode {

    val id : Int

    var partlyProcessed : Boolean

    var processed : Boolean

    var children : MutableList<GraphNode>

    val edges : MutableList<GraphEdge>

    var maxDistance : Int

    var previous : GraphNode

    fun addChild(child : GraphNode)

    fun addChildren(children : List<GraphNode>)
}