package implementations

import datastructures.set.SetNode


class SetNodeImpl(override val id : Int) : SetNode {

    override var sum = id.toLong()
    override var parent: SetNode = this
    override var size = 1
    override var rank = 0

}