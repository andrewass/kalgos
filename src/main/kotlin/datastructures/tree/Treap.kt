package datastructures.tree

import java.util.*

class Treap {

    var root: TreapNode? = null

    /**
     *
     */
    fun insert(node: TreapNode) {
        var currentNode = root
        var parent: TreapNode? = null
        while (currentNode != null && currentNode.priority > node.priority) {
            parent = currentNode
            currentNode = if (node.value < currentNode.value) {
                currentNode.leftChild
            } else {
                currentNode.rightChild
            }
        }
        var splitResult = split(currentNode, node.value)
        when {
            parent == null -> root = node
            node.value < parent.value -> parent.leftChild = node
            else -> parent.rightChild = node
        }
        node.leftChild = splitResult.left
        node.rightChild = splitResult.right
    }

    /**
     * Exposed function for deleting a node from the Treap
     */
    fun delete(value: Int) {
        delete(root, value, null)
    }

    /**
     * Private recursive helper function for deleting a node from the Treap
     */
    private fun delete(node: TreapNode?, value: Int, parent: TreapNode?) {
        when {
            node == null -> return
            node.value > value -> delete(node.leftChild, value, node)
            node.value < value -> delete(node.rightChild, value, node)
            else -> {
                val mergedTree = merge(node.leftChild, node.rightChild)
                when {
                    parent == null -> root = mergedTree
                    node == parent.leftChild -> parent.leftChild = mergedTree
                    else -> parent.rightChild = mergedTree
                }
            }
        }
    }


    /**
     * Splits the tree rooted at node, into left and right tree
     */
    private fun split(treap: TreapNode?, splitValue: Int): SplitWrapper {

        return if (treap == null) {
            SplitWrapper(null, null)
        } else {
            if (treap.value < splitValue) {
                val res = split(treap.rightChild, splitValue)
                treap.rightChild = res.left
                SplitWrapper(treap, res.right)
            } else {
                val res = split(treap.leftChild, splitValue)
                treap.leftChild = res.right
                SplitWrapper(res.left, treap)
            }
        }
    }


    /**
     *
     */
    private fun merge(left: TreapNode?, right: TreapNode?): TreapNode? {
        return when {
            left == null -> right
            right == null -> left
            else -> {
                if (left.priority > right.priority) {
                    left.rightChild = merge(left.rightChild, right)
                    left
                } else {
                    right.leftChild = merge(left, right.leftChild)
                    right
                }
            }
        }
    }

    /**
     * Collects a list with [TreapNode] in ascending order
     *
     * @return a list of nodes in ascending order
     */
    fun inOrderList(): List<TreapNode> {
        val inOrderList = LinkedList<TreapNode>()
        fillInOrderList(root, inOrderList)
        return inOrderList
    }

    /**
     * Helper function for collecting the in-order list of [TreapNode]
     *
     * @param node current node
     * @return a linked list storing the nodes
     */
    private fun fillInOrderList(node: TreapNode?, inOrderList: LinkedList<TreapNode>) {
        if (node != null) {
            fillInOrderList(node.leftChild, inOrderList)
            inOrderList.addLast(node)
            fillInOrderList(node.rightChild, inOrderList)
        }
    }


    private class SplitWrapper(var left: TreapNode?, var right: TreapNode?)
}