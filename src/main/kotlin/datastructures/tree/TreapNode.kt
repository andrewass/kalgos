package datastructures.tree

interface TreapNode {

    var leftChild : TreapNode?
    var rightChild : TreapNode?
    var value : Int
    var priority : Int
    var size : Int
}