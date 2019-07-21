package datastructures.rangequery

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals


class BinaryIndexedTreeTest {

    private lateinit var binaryIndexedTree: BinaryIndexedTree
    private val capacity = 10

    @Test
    fun shouldFindCorrectRangeSumsWithoutAnyUpdates() {
        val listOfLongs = fillList()
        binaryIndexedTree = BinaryIndexedTree(capacity)
        for (i in listOfLongs.indices) {
            binaryIndexedTree.addValue(i + 1, listOfLongs[i])
        }
        val rangeSumOfLastElement = binaryIndexedTree.getSum(10) - binaryIndexedTree.getSum(9)
        val rangeSumFrom4thTo9thElement = binaryIndexedTree.getSum(9) - binaryIndexedTree.getSum(3)

        assertEquals(rangeSumOfLastElement, 10)
        assertEquals(rangeSumFrom4thTo9thElement, 121)
        assertEquals(binaryIndexedTree.getSum(capacity), 194)
        assertEquals(binaryIndexedTree.getSum(1), 3)
    }

    @Test
    fun shouldFindCorrectRangeSumsWithoutUpdates() {
        val listOfLongs = fillList()
        binaryIndexedTree = BinaryIndexedTree(capacity)
        for (i in listOfLongs.indices) {
            binaryIndexedTree.addValue(i + 1, listOfLongs[i])
        }
        binaryIndexedTree.addValue(2, -15)
        binaryIndexedTree.addValue(8, 10)
        assertEquals(binaryIndexedTree.getSum(capacity), 189)
    }

    @Test
    fun shouldFindCorrectRangeSumWhenSomeCellsHoldsDefaultValue() {
        binaryIndexedTree = BinaryIndexedTree(capacity)
        binaryIndexedTree.addValue(2, 15)
        binaryIndexedTree.addValue(8, 10)
        assertEquals(binaryIndexedTree.getSum(capacity), 25)
        assertEquals(binaryIndexedTree.getSum(1), 0)
    }

    @Test
    fun shouldThrowExceptionWhenIndexIsLessThanOne() {
        binaryIndexedTree = BinaryIndexedTree(capacity)
        assertThrows(IllegalArgumentException::class.java) {
            binaryIndexedTree.addValue(0, 10)
        }
    }

    @Test
    fun shouldThrowExceptionWhenIndexIsAboveCapacity() {
        binaryIndexedTree = BinaryIndexedTree(capacity)
        assertThrows(IllegalArgumentException::class.java) {
            binaryIndexedTree.addValue(11, 10)
        }
    }

    private fun fillList() = listOf<Long>(3, 55, 5, 21, 3, 6, 74, 8, 9, 10)

}