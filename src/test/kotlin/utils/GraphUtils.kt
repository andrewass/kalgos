package utils

import implementations.GraphNodeImpl


fun createDirectedAcyclicGraph(): List<GraphNodeImpl> {
    val nodeList = mutableListOf<GraphNodeImpl>()
    for (i in 0..6) {
        nodeList.add(GraphNodeImpl(i))
    }
    nodeList[0].addChildren(listOf(nodeList[1], nodeList[2], nodeList[3]))
    nodeList[1].addChildren(listOf(nodeList[3], nodeList[4]))
    nodeList[3].addChild(nodeList[5])
    nodeList[6].addChildren(listOf(nodeList[2], nodeList[4]))

    return nodeList
}