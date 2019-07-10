package datastructures.tree

import implementations.BinaryTreeNodeImpl
import org.junit.jupiter.api.Test

class BinarySearchTreeTests {

    @Test
    fun test1(){
        val binarySearchTree = BinarySearchTree()
        binarySearchTree.insertNode(BinaryTreeNodeImpl(5))
        binarySearchTree.insertNode(BinaryTreeNodeImpl(3))
        binarySearchTree.insertNode(BinaryTreeNodeImpl(7))
        binarySearchTree.insertNode(BinaryTreeNodeImpl(1))
    }
}