package entities

class GraphEdge(val from: GraphNode, val to: GraphNode, val weight: Int = 0){

    var visited = false
}