package datastructures.tree

import implementations.TreapNodeImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TreapTest {

    lateinit var treap: Treap

    @BeforeEach
    fun init() {
        treap = createTreap()
    }

    @Test
    fun shouldBeABinarySearchTree() {
        val inOrderList = treap.inOrderList()
        assertTrue(isInOrder(inOrderList))
        assertEquals(50, inOrderList.size)
    }

    @Test
    fun shouldBeAbleToLookUpNodesInTreap() {
        assertEquals(treap.find(5)!!.value, 5)
        assertEquals(treap.find(13)!!.value, 13)
        assertEquals(treap.find(37)!!.value, 37)
        assertNull(treap.find(100))
    }

    @Test
    fun shouldBeInOrderAfterDeleteOperations() {
        treap.delete(5)
        treap.delete(23)
        treap.delete(11)
        treap.delete(33)
        treap.delete(3)

        val inOrderList = treap.inOrderList()
        assertTrue(isInOrder(inOrderList))
        assertEquals(45, inOrderList.size)

        assertNull(treap.find(5))
        assertNull(treap.find(23))
        assertNull(treap.find(11))
        assertNull(treap.find(33))
        assertNull(treap.find(3))
    }

    private fun createTreap(): Treap {
        val treap = Treap()
        for (i in 1..50) {
            treap.insert(TreapNodeImpl(i.toLong()))
        }
        return treap
    }

    private fun isInOrder(list: List<TreapNode>): Boolean {
        var prevValue: Long? = null
        for (node in list) {
            when {
                prevValue != null && node.value < prevValue -> return false
                else -> prevValue = node.value
            }
        }
        return true
    }
}