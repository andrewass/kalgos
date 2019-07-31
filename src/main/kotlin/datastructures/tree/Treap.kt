package datastructures.tree

import java.util.*

/**
 * Implementation of [Treap](https://en.wikipedia.org/wiki/Treap)
 */
class Treap {

    var root: TreapNode? = null

    /**
     * Insert a node to the treap
     *
     * @param node Node to be inserted
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
        val splitResult = split(currentNode, node.value)
        when {
            parent == null -> root = node
            node.value < parent.value -> parent.leftChild = node
            else -> parent.rightChild = node
        }
        node.leftChild = splitResult.left
        node.rightChild = splitResult.right
    }

    /**
     * Delete a node with a given value from the Treap
     *
     * @param value Value of the node to be deleted
     */
    fun delete(value: Long) {
        delete(root, value, null)
    }

    /**
     * Search for a node with a given value in the Treap
     *
     * @param value Value of the node to be retrieved
     * @return a node with a matching value, else null
     */
    fun find(value: Long): TreapNode? {
        return find(root, value)
    }

    /**
     * Helper function for the exposed find function.
     *
     * @param node Root node of current subtree
     * @param value  Value of the node to be retrieved
     * @return a node with a matching value, else null
     */
    private fun find(node: TreapNode?, value: Long): TreapNode? {
        return when {
            node == null || node.value == value -> node
            value < node.value -> find(node.leftChild, value)
            else -> find(node.rightChild, value)
        }
    }

    /**
     * Helper function for the exposed delete function.
     *
     * @param node Root node of current subtree
     * @param value  Value of the node to be deleted
     * @param parent Parent node of current node
     */
    private fun delete(node: TreapNode?, value: Long, parent: TreapNode?) {
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
     * Splits the tree rooted at node, into a left and right Treap
     *
     * @param node Root node of current subtree
     * @param splitValue Value used to decide if nodes belong to left or right Treap
     * @return a wrapper object storing a left and right Treap
     */
    private fun split(node: TreapNode?, splitValue: Long): SplitWrapper {

        return if (node == null) {
            SplitWrapper(null, null)
        } else {
            if (node.value < splitValue) {
                val res = split(node.rightChild, splitValue)
                node.rightChild = res.left
                SplitWrapper(node, res.right)
            } else {
                val res = split(node.leftChild, splitValue)
                node.leftChild = res.right
                SplitWrapper(res.left, node)
            }
        }
    }

    /**
     * Merge a left and right Treap into one
     *
     * @param left Current root of left Treap
     * @param right Current root right Treap
     * @return the merged Treap
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
     * @param node Root node of current subtree
     * @param inOrderList A list storing each node in the Treap
     */
    private fun fillInOrderList(node: TreapNode?, inOrderList: LinkedList<TreapNode>) {
        if (node != null) {
            fillInOrderList(node.leftChild, inOrderList)
            inOrderList.addLast(node)
            fillInOrderList(node.rightChild, inOrderList)
        }
    }

    /**
     * Private class for storing the return value of a split operation
     *
     * @param left Left Treap during a split
     * @param right Right Treap during a split
     */
    private class SplitWrapper(var left: TreapNode?, var right: TreapNode?)
}