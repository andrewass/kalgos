package datastructures.tree

interface TreapNode {

    var leftChild : TreapNode?
    var rightChild : TreapNode?
    var value : Long
    var priority : Int
    var size : Int
}