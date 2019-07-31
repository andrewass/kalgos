package implementations

import datastructures.tree.TreapNode
import kotlin.random.Random

class TreapNodeImpl(override var value: Long) : TreapNode {
    override var leftChild: TreapNode? = null
    override var rightChild: TreapNode? = null
    override var priority: Int = Random.nextInt(Int.MAX_VALUE)
    override var size = 0
}