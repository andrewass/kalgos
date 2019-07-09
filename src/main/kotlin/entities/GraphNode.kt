package entities

interface Node {

    val startTime : Long
    val finishTime : Long
    var visited : Boolean
    var child : List<Node>
    var parent : Node?

}