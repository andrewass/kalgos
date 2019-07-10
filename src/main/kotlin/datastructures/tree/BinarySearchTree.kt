package datastructures.tree

import entities.BinaryTreeNode

/**
 * An implementation of a Binary Search Tree, constructed by [BinaryTreeNode]
 */
class BinarySearchTree {

    private var rootNode: BinaryTreeNode? = null

    /**
     * Inserts a new [BinaryTreeNode], adding it to the tree as a leaf where its position is
     * based on its comparison value.
     */
    fun insertNode(newNode: BinaryTreeNode) {
        var currentNode = rootNode
        var leafParent: BinaryTreeNode? = null
        while (currentNode != null) {
            leafParent = currentNode
            currentNode = if (lessThanCurrentNode(newNode, currentNode)) {
                currentNode.leftChild
            } else {
                currentNode.rightChild
            }
        }
        when {
            leafParent == null -> rootNode = newNode
            lessThanCurrentNode(newNode, leafParent) -> leafParent.leftChild = newNode
            else -> leafParent.rightChild = newNode
        }
    }

    fun removeNode() {

    }

    private fun lessThanCurrentNode(newNode: BinaryTreeNode, currentNode: BinaryTreeNode): Boolean {
        return newNode.getValue() < currentNode.getValue()
    }

}