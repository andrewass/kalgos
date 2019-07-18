package datastructures.set

interface SetNode {

    val id: Int

    var parent: SetNode

    var size: Int

    var rank : Int

    var sum : Long
}