package algorithms.flow

class FlowEdge(val to: FlowNode, val capacity : Long, val flowVal : Long){

    var flow = 0L
    var reverseEdge : FlowEdge? = null

    init {
        flow = flowVal
    }
}