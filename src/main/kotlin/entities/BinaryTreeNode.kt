package entities

interface BinaryTreeNode {

    var leftChild : BinaryTreeNode?
    var rightChild : BinaryTreeNode?
    var parent : BinaryTreeNode?
    var size : Int

    fun getValue() : Int

    fun getId() : Long
}