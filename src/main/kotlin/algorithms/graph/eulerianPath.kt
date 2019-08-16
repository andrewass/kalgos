package algorithms.graph

import entities.GraphNode



fun eulerianPathDirGraph(nodes : List<GraphNode>) : List<GraphNode> {

    val singleNodes = nodes.asSequence()
            .filter { it.edges.isEmpty() && it.reverseEdges.isEmpty() }
            .toList()

    val edgeNodes = nodes.asSequence()
            .filter { it.edges.isNotEmpty() || it.reverseEdges.isNotEmpty() }
            .toList()

    return if(eulerianPathExists(edgeNodes)){
        getPath(edgeNodes)
    } else {
        emptyList()
    }
}

private fun eulerianPathExists(node : List<GraphNode>) : Boolean{
    return true
}

private fun getPath(nodes : List<GraphNode>) : List<GraphNode> {
    return emptyList()
}