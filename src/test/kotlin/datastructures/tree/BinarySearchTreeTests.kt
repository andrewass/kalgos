package datastructures.tree

import entities.BinaryTreeNode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import utils.createBinarySearchTree
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class BinarySearchTreeTests {

    private lateinit var tree : BinarySearchTree

    @BeforeEach
    fun init(){
        tree = createBinarySearchTree(15)
    }

    @Test
    fun treeShouldBeABinarySearchTree(){
        val inOrderList = tree.inOrderList()
        assertTrue(isInOrder(inOrderList))
    }

    @Test
    fun shouldBeAbleToLookUpNodesInTree(){
        assertEquals(tree.getNode(tree.rootNode, 5)!!.getValue(), 5)
        assertEquals(tree.getNode(tree.rootNode, 13)!!.getValue(), 13)
        assertEquals(tree.getNode(tree.rootNode, 41)!!.getValue(), 41)
        assertNull(tree.getNode(tree.rootNode, 44))
    }

    @Test
    fun treeShouldBeInCorrectOrderAfterDeletes(){
        tree.deleteNode(tree.getNode(tree.rootNode, 5)!!)
        tree.deleteNode(tree.getNode(tree.rootNode, 13)!!)
        tree.deleteNode(tree.getNode(tree.rootNode, 41)!!)
        val inOrderList = tree.inOrderList()

        assertTrue(isInOrder(inOrderList))
        assertEquals(inOrderList.size, 12)
        assertNull(tree.getNode(tree.rootNode, 5))
        assertNull(tree.getNode(tree.rootNode, 13))
        assertNull(tree.getNode(tree.rootNode, 41))
    }

    @Test
    fun shouldFindCorrectMinimumValue(){
        assertEquals(tree.findMinimum(tree.rootNode)!!.getValue(), 2)
    }

    @Test
    fun shouldFindCorrectMaximumValue(){
        assertEquals(tree.findMaximum(tree.rootNode)!!.getValue(), 47)
    }


    private fun isInOrder(list: List<BinaryTreeNode>): Boolean {
        var prevValue : Int? = null
        for(node in list){
            when {
                prevValue != null && node.getValue() < prevValue -> return false
                else -> prevValue = node.getValue()
            }
        }
        return true
    }
}