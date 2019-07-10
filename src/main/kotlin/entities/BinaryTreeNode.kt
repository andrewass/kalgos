package entities

interface BinaryTreeNode {

    var leftChild : BinaryTreeNode?
    var rightChild : BinaryTreeNode?
    var parent : BinaryTreeNode?

    fun getValue() : Int

}