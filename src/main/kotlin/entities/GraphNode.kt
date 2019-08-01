package entities

import com.sun.corba.se.impl.orbutil.graph.Graph

interface GraphNode {

    val id : Int

    var partlyProcessed : Boolean

    var processed : Boolean

    var neighbours : MutableList<GraphNode>

    val edges : MutableList<GraphEdge>

    var maxDistance : Int

    var previous : GraphNode

    //For use with Strongly Connected Components
    var index : Int
    var lowLink : Int
    var onStack : Boolean

    fun addNeighbour(child : GraphNode)

    fun addNeighbours(children : List<GraphNode>)
}