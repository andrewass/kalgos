package datastructures.tree

import entities.BinaryTreeNode
import java.util.*

/**
 * An implementation of a Binary Search Tree, using [BinaryTreeNode] as tree nodes
 */
class BinarySearchTree {

    var rootNode: BinaryTreeNode? = null
        private set

    /**
     * Inserts a new [BinaryTreeNode], adding it to the tree as a leaf where its position is
     * based on its comparison value.
     *
     * @param newNode the node being inserted into the tree
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
        newNode.parent = leafParent
        when {
            leafParent == null -> rootNode = newNode
            lessThanCurrentNode(newNode, leafParent) -> leafParent.leftChild = newNode
            else -> leafParent.rightChild = newNode
        }
    }

    /**
     * Deletes a [BinaryTreeNode] from the tree.
     *
     * @param delNode the node being deleted from the tree
     */
    fun deleteNode(delNode: BinaryTreeNode) {
        when {
            delNode.leftChild == null -> transplant(delNode, delNode.rightChild)
            delNode.rightChild == null -> transplant(delNode, delNode.leftChild)
            else -> {
                val minRightNode = findMinimum(delNode.rightChild)
                if(minRightNode != delNode.parent){
                    transplant(minRightNode!!, minRightNode.rightChild)
                    minRightNode.rightChild = delNode.rightChild
                    //TODO : Fix possible NPE due to missing parent
                    minRightNode.rightChild!!.parent = minRightNode

                }
                transplant(delNode, minRightNode)
                minRightNode!!.leftChild = delNode.leftChild
                minRightNode.leftChild!!.parent = minRightNode
            }
        }
    }


    fun getNode(node : BinaryTreeNode?, searchId : Long) : BinaryTreeNode?{
        return when {
            node == null || node.getId() == searchId -> node
            searchId < node.getId() -> getNode(node.leftChild, searchId)
            else -> getNode(node.rightChild, searchId)
        }
    }

    /**
     * Removes the linking between a sub root and its parent,
     * and creates a direct link between the root child and its
     * previous grandparent.
     *
     * @param root representing a sub root node in the tree.
     * @param rootChild representing a child of the sub root
     */
    private fun transplant(root: BinaryTreeNode, rootChild: BinaryTreeNode?) {
        when {
            root.parent == null -> rootNode = rootChild
            root == root.parent!!.leftChild -> root.parent!!.leftChild = rootChild
            else -> root.parent!!.rightChild = rootChild
        }
        if (rootChild != null) {
            rootChild.parent = root.parent
        }
    }

    /**
     * Find the [BinaryTreeNode] with the lowest value in the tree
     *
     * @param root representing a selected root node from the entire tree, or a sub part of it.
     * @return the node with the lowest value in the tree. Null if tree is empty
     */
    fun findMinimum(root: BinaryTreeNode?): BinaryTreeNode? {
        var currentNode: BinaryTreeNode? = root ?: return null
        while (currentNode?.leftChild != null) {
            currentNode = currentNode.leftChild
        }
        return currentNode
    }

    /**
     * Find the [BinaryTreeNode] with the largest value in the tree
     *
     * @param root representing a selected root node from the entire tree, or a sub part of it.
     * @return the node with the largest value in the tree. Null if tree is empty
     */
    fun findMaximum(root: BinaryTreeNode?): BinaryTreeNode? {
        var currentNode: BinaryTreeNode? = root ?: return null
        while (currentNode?.rightChild != null) {
            currentNode = currentNode.rightChild
        }
        return currentNode
    }

    fun inOrderList() : List<BinaryTreeNode>{
        val inOrderList = LinkedList<BinaryTreeNode>()
        fillInOrderList(rootNode, inOrderList)
        return inOrderList
    }

    private fun fillInOrderList(node : BinaryTreeNode?, inOrderList: LinkedList<BinaryTreeNode>){
        if(node != null){
            fillInOrderList(node.leftChild, inOrderList)
            inOrderList.addLast(node)
            fillInOrderList(node.rightChild, inOrderList)
        }
    }

    private fun lessThanCurrentNode(newNode: BinaryTreeNode, currentNode: BinaryTreeNode): Boolean {
        return newNode.getValue() < currentNode.getValue()
    }
}