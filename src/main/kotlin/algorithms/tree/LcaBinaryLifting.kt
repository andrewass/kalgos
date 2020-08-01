package algorithms.tree

import entities.TreeNode
import kotlin.math.ceil
import kotlin.math.log2

/**
 * Structure for finding the lowest common ancestor of any two given nodes
 * of the tree.
 *
 * Uses binary lifting to answer each query in O(log N) time
 */
class LcaBinaryLifting(private val nodes: List<TreeNode>, root: TreeNode) {

    private val n = nodes.size
    private val maxJumps = ceil(log2(nodes.size.toDouble())).toInt()
    private val jumps = Array(n) { Array(maxJumps) { -1 } }

    init {
        setDepthAndParent(0, root)
        fillRestOfJumpArray()
    }

    /**
     * Return the lowest common ancestor of node A and node B
     */
    fun findLca(nodeA : TreeNode, nodeB : TreeNode) : TreeNode {
        val orderedNodes = orderNodes(nodeA, nodeB)
        val nodePair = liftDeepestNode(orderedNodes.first, orderedNodes.second)
        return if(nodePair.first == nodePair.second){
            nodePair.second
        } else {
            liftBothNodesToCommonAncestor(nodePair.first.id, nodePair.second.id)
        }
    }

    /**
     * Initially, first and second node are at equal depth.
     * Lift both nodes up the tree. Each move decreases depth by a power of 2.
     * Continue lifting both nodes until the first common ancestor is found
     */
    private fun liftBothNodesToCommonAncestor(initFirstId: Int, initSecondId: Int): TreeNode {
        var exponent = log2(nodes[initFirstId].depth.toDouble()).toInt()
        var firstId = initFirstId
        var secondId = initSecondId
        for(i in exponent downTo 0){
            if(jumps[firstId][i] != jumps[secondId][i]) {
                firstId = jumps[firstId][i]
                secondId = jumps[secondId][i]
            }
        }
        return nodes[jumps[firstId][0]]
    }

    /**
     * Lift the deepest node up the tree, until reaching same depth as highest
     *
     * Return true if highest is parent of deepest, else false
     */
    private fun liftDeepestNode(deepest: TreeNode, highest: TreeNode): Pair<TreeNode, TreeNode> {
        var gap = deepest.depth - highest.depth
        var currentNode = deepest
        while(gap > 0){
            val maxJump = Integer.highestOneBit(gap)
            val parentIndex = log2(maxJump.toDouble()).toInt()
            gap -= maxJump
            currentNode = nodes[jumps[currentNode.id][parentIndex]]
        }
        return Pair(currentNode, highest)
    }


    /**
     * Set depth and closest parent in the jump array by using a depth first search of
     * the tree
     */
    private fun setDepthAndParent(depth: Int, current: TreeNode) {
        current.depth = depth
        if (current.parent != null) {
            jumps[current.id][0] = current.parent!!.id
        }
        current.children.forEach { setDepthAndParent(depth + 1, it) }
    }

    /**
     * Fill the remaining of the jump array by dynamic programming
     */
    private fun fillRestOfJumpArray() {
        for (j in 1 until maxJumps) {
            for (i in 0 until n) {
                val parent = jumps[i][j - 1]
                if (parent != -1) {
                    jumps[i][j] = jumps[parent][j - 1]
                }
            }
        }
    }

    /**
     * Helper function to order a pair of nodes by depth
     */
    private fun orderNodes(nodeA: TreeNode, nodeB: TreeNode): Pair<TreeNode, TreeNode> {
        return if(nodeA.depth > nodeB.depth){
            Pair(nodeA, nodeB)
        } else {
            Pair(nodeB, nodeA)
        }
    }
}
