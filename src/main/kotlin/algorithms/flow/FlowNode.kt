package algorithms.flow

interface FlowNode {

    var level : Int
    val id : Int
    val edges : List<FlowEdge>
}


