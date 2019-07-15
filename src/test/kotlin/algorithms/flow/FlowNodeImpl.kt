package algorithms.flow

class FlowNodeImpl(override val id: Int) : FlowNode {

    override var level = 0

    override val edges = mutableListOf<FlowEdge>()
}