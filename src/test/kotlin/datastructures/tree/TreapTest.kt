package datastructures.tree

import implementations.TreapNodeImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TreapTest {

    lateinit var treap : Treap

    @BeforeEach
    fun init(){
        treap = createTreap()
    }

    @Test
    fun shouldBeABinarySearchTree(){
        val inOrderList = treap.inOrderList()
        assertTrue(isInOrder(inOrderList))
        assertEquals(50, inOrderList.size)
    }

    @Test
    fun shouldBeInOrderAfterDeleteOperations(){
        treap.delete(5)
        treap.delete(23)
        treap.delete(11)
        treap.delete(33)
        treap.delete(3)

        val inOrderList = treap.inOrderList()
        assertTrue(isInOrder(inOrderList))
        assertEquals(45, inOrderList.size)
    }

    private fun createTreap() : Treap {
        val treap = Treap()
        for (i in 1..50) {
            treap.insert(TreapNodeImpl(i))
        }
        return treap
    }

    private fun isInOrder(list: List<TreapNode>): Boolean {
        var prevValue : Int? = null
        for(node in list){
            when {
                prevValue != null && node.value < prevValue -> return false
                else -> prevValue = node.value
            }
        }
        return true
    }
}