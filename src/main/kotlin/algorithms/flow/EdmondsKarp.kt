package algorithms.flow

import java.util.*
import kotlin.math.min

class EdmondsKarp(val source: FlowNode, val sink: FlowNode, val nodes: Int) {

    lateinit var inEdgeOfNode: Array<FlowEdge?>
    private var maxFlow = 0L

    /**
     * While there exists at least one augmenting path from source to sink,
     * increase the max flow with the bottleneck value of current path.
     */
    fun getMaxFlow(): Long {
        do {
            inEdgeOfNode = Array(size = nodes) { null }
            findAugmentingPath()
            if(augmentingPathExists()){
                updatePathWithBottleNeckValue()
            }
        } while (augmentingPathExists())

        return maxFlow
    }

    /**
     * Find an augmenting path from source to sink, if it exists, using BFS from source node
     */
    private fun findAugmentingPath() {
        val queue = LinkedList<FlowNode>()
        queue.add(source)

        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()
            if(currentNode == sink){
                return
            }
            for (edge in currentNode.edges) {
                val remainingCapacity = edge.capacity - edge.flow
                val nextNode = edge.to
                if (remainingCapacity > 0 && inEdgeOfNode[nextNode.id] == null && nextNode != source) {
                    inEdgeOfNode[nextNode.id] = edge
                    queue.addLast(nextNode)
                }
            }
        }
    }

    /**
     * When a augmenting path is found, find the edge with least remaining capacity, and use its remaining capacity
     * as the bottleneck-value. Then increase the current flow of each edge on the path with the bottleneck-value,
     * and subtract the bottleneck-value from the current flow of each reverse edge.
     */
    private fun updatePathWithBottleNeckValue() {
        var bottleneckValue = Long.MAX_VALUE
        var currentEdge : FlowEdge? = inEdgeOfNode[sink.id]

        while (currentEdge != null){
            bottleneckValue = min(bottleneckValue, currentEdge.capacity - currentEdge.flow)
            currentEdge = inEdgeOfNode[currentEdge.from.id]
        }

        currentEdge = inEdgeOfNode[sink.id]
        while (currentEdge != null){
            currentEdge.flow += bottleneckValue
            currentEdge.reverseEdge.flow -= bottleneckValue
            currentEdge = inEdgeOfNode[currentEdge.from.id]
        }
        maxFlow += bottleneckValue
    }

    /**
     * An augmenting path exists of there is an incoming edge to the sink node on the path
     */
    private fun augmentingPathExists() = inEdgeOfNode[sink.id] != null
}