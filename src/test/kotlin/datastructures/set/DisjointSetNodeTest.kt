package datastructures.set

import implementations.SetNodeImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DisjointSetNodeTest{

    @Test
    fun allItemsShouldBeInUniqueSets(){
        val disjointSet = createDisjointSet()
        for(i in 1..8){
            val setRoot = disjointSet.find(i)
            assertEquals(1, setRoot.size)
            assertEquals(i, setRoot.id)
        }
    }

    @Test
    fun itemsShouldBeDividedIntoTwoSets(){
        val disjointSet = createDisjointSet()
        disjointSet.union(1,2)
        disjointSet.union(3,4)
        disjointSet.union(2,3)
        disjointSet.union(5,6)
        disjointSet.union(1,6)
        disjointSet.union(7,8)

        val firstRoot = disjointSet.find(3)
        val secondRoot = disjointSet.find(7)

        assertEquals(6, firstRoot.size)
        assertEquals(2, secondRoot.size)
    }

    @Test
    fun allItemsShouldBeMergedIntoSameSet(){
        val disjointSet = createDisjointSet()
        disjointSet.union(1,2)
        disjointSet.union(3,4)
        disjointSet.union(5,8)
        disjointSet.union(7,6)
        disjointSet.union(2,3)
        disjointSet.union(5,7)
        disjointSet.union(1,8)

        val setRoot = disjointSet.find(1)
        assertEquals(8, setRoot.size)
        for(i in 2..8){
            assertEquals(setRoot, disjointSet.find(i))
        }
    }

    private fun createDisjointSet(): DisjointSet {
        val disjointSet = DisjointSet()
        for(i in 0..8){
            disjointSet.addSet(SetNodeImpl(i))
        }
        return disjointSet
    }
}