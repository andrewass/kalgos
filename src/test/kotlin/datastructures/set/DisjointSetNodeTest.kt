package datastructures.set

import implementations.SetNodeImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DisjointSetNodeTest{

    @Test
    fun allItemsShouldBeInUniqueSets(){
        val sets = createDisjointSet()
        for(i in 1..8){
            val setRoot = sets.find(sets.getSet(i))
            assertEquals(1, setRoot.size)
            assertEquals(i, setRoot.id)
        }
    }

    @Test
    fun itemsShouldBeDividedIntoTwoSets(){
        val sets = createDisjointSet()
        sets.union(sets.getSet(1), sets.getSet(2))
        sets.union(sets.getSet(3), sets.getSet(4))
        sets.union(sets.getSet(2), sets.getSet(3))
        sets.union(sets.getSet(5), sets.getSet(6))
        sets.union(sets.getSet(1), sets.getSet(6))
        sets.union(sets.getSet(7), sets.getSet(8))

        val firstRoot = sets.find(sets.getSet(3))
        val secondRoot = sets.find(sets.getSet(7))

        assertEquals(6, firstRoot.size)
        assertEquals(2, secondRoot.size)
    }

    @Test
    fun allItemsShouldBeMergedIntoSameSet(){
        val sets = createDisjointSet()
        sets.union(sets.getSet(1), sets.getSet(2))
        sets.union(sets.getSet(3), sets.getSet(4))
        sets.union(sets.getSet(5), sets.getSet(8))
        sets.union(sets.getSet(7), sets.getSet(6))
        sets.union(sets.getSet(2), sets.getSet(3))
        sets.union(sets.getSet(5), sets.getSet(7))
        sets.union(sets.getSet(1), sets.getSet(8))

        val setRoot = sets.find(sets.getSet(1))
        assertEquals(8, setRoot.size)
        for(i in 2..8){
            assertEquals(setRoot, sets.find(sets.getSet(i)))
        }
    }

    @Test
    fun structureShouldBeValidAfterMovingItemBetweenSets(){
        val sets = createDisjointSet()

        sets.union(sets.getSet(1), sets.getSet(2))
        sets.moveToSet(oldItem = sets.getSet(3), destination = sets.getSet(4), newItem = SetNodeImpl(3))
        sets.union(sets.getSet(3), sets.getSet(5))

        var setOfItem = sets.find(sets.getSet(4))
        assertEquals(setOfItem.size, 3)
        assertEquals(setOfItem.sum, 12)

        sets.moveToSet(oldItem = sets.getSet(4), destination = sets.getSet(1), newItem = SetNodeImpl(4))

        setOfItem = sets.find(sets.getSet(4))
        assertEquals(setOfItem.size, 3)
        assertEquals(setOfItem.sum, 7)

        setOfItem = sets.find(sets.getSet(3))
        assertEquals(setOfItem.size, 2)
        assertEquals(setOfItem.sum, 8)

        sets.moveToSet(oldItem = sets.getSet(3), destination = sets.getSet(5), newItem = SetNodeImpl(3))

        setOfItem = sets.find(sets.getSet(3))
        assertEquals(setOfItem.size, 2)
        assertEquals(setOfItem.sum, 8)
    }

    private fun createDisjointSet(): DisjointSet {
        val disjointSet = DisjointSet()
        for(i in 0..8){
            disjointSet.addSet(SetNodeImpl(i))
        }
        return disjointSet
    }
}