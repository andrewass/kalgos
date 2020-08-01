package implementations

import entities.BinaryTreeNode

class BinaryTreeNodeImpl(private val value : Int, private val id : Long) : BinaryTreeNode {

    override var size: Int = 0

    override var leftChild: BinaryTreeNode? = null

    override var rightChild: BinaryTreeNode? = null

    override var parent : BinaryTreeNode? = null

    override fun getValue() = value

    override fun getId(): Long {
        return id
    }
}