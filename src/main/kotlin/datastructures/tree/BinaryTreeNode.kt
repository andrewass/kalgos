package datastructures.tree

interface BinaryTreeNode {

    var leftChild : BinaryTreeNode?
    var rightChild : BinaryTreeNode?
    var parent : BinaryTreeNode?

    fun getValue() : Int

    fun getId() : Long
}