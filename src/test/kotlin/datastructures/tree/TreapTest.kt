package datastructures.tree

import implementations.TreapNodeImpl
import org.junit.jupiter.api.Test

class TreapTest {

    @Test
    fun test1() {
        val treap = Treap()
        for(i in 1..20){
            treap.insert(TreapNodeImpl(i))
        }

    }
}