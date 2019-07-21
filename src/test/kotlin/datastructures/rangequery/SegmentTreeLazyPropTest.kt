package datastructures.rangequery

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class SegmentTreeLazyPropTest {

    @Test
    fun shouldFindCorrectRangeSumsWithoutUsingInputArray() {
        val size = 15
        val segmentTree = SegmentTreeLazyProp(size)

        segmentTree.update(value = 2L, left = 1, right = 15)
        assertEquals(30L, segmentTree.get(left = 1, right = size))

        segmentTree.update(value = -1L, left = 5, right = 10)
        assertEquals(6L, segmentTree.get(left = 5, right = 10))
        assertEquals(24L, segmentTree.get(left = 1, right = size))

        segmentTree.update(value = 3L, left = 8, right = 13)
        assertEquals(15L, segmentTree.get(left = 5, right = 10))
    }

    @Test
    fun shouldFindCorrectRangeSumsWhenUsingInputArray() {
        val size = 20
        val list = (1..size).asSequence().toList()
        val segmentTree = SegmentTreeLazyProp(list)

        assertEquals(210L, segmentTree.get(left = 1, right = size))

        segmentTree.update(value = 2L, left = 5, right = 10)
        assertEquals(222L, segmentTree.get(left = 1, right = size))
    }

    @Test
    fun shouldHandleUpdatedAndQueriesOnSingleRangeNodes() {
        val size = 15
        val segmentTree = SegmentTreeLazyProp(size)

        assertEquals(0L, segmentTree.get(left = 1, right = size))

        segmentTree.update(value = 1L, left = 1, right = 1)
        assertEquals(1L, segmentTree.get(left = 1, right = 1))

        segmentTree.update(value = 1L, left = 9, right = 9)
        assertEquals(1L, segmentTree.get(left = 9, right = 9))

        segmentTree.update(value = 1L, left = size, right = size)
        assertEquals(1L, segmentTree.get(left = size, right = size))

        assertEquals(3L, segmentTree.get(left = 1, right = size))
    }
}