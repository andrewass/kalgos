package utils

import implementations.GraphNodeImpl


fun createDirectedAcyclicGraph(): List<GraphNodeImpl> {
    val nodeList = mutableListOf<GraphNodeImpl>()
    for (i in 0..6) {
        nodeList.add(GraphNodeImpl(i))
    }
    nodeList[0].addNeighbours(listOf(nodeList[1], nodeList[2], nodeList[3]))
    nodeList[1].addNeighbours(listOf(nodeList[3], nodeList[4]))
    nodeList[3].addNeighbour(nodeList[5])
    nodeList[6].addNeighbours(listOf(nodeList[2], nodeList[4]))

    return nodeList
}