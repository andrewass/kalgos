package algorithms.flow

class FlowEdge(
        val from: FlowNode,
        val to: FlowNode,
        val capacity: Long = 0L,
        val flowVal: Long = 0L
) {
    var flow = 0L
    lateinit var reverseEdge: FlowEdge

    init {
        flow = flowVal
    }
}