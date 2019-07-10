package entities

interface BinaryTreeNode {

    var leftChild : BinaryTreeNode?
    var rightChild : BinaryTreeNode?

    fun getValue() : Int

}