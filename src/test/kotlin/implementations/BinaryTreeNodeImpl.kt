package implementations

import entities.BinaryTreeNode

class BinaryTreeNodeImpl(private val value : Int) : BinaryTreeNode {

    override var leftChild: BinaryTreeNode? = null

    override var rightChild: BinaryTreeNode? = null

    override var parent : BinaryTreeNode? = null

    override fun getValue() = value
}