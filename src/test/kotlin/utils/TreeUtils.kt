package utils

import datastructures.tree.BinarySearchTree
import implementations.BinaryTreeNodeImpl
import java.math.BigInteger
import java.util.*
import java.util.Collections.shuffle
import java.util.stream.Stream
import java.util.stream.Stream.*
import kotlin.streams.toList

fun createBinarySearchTree(treeSize: Int): BinarySearchTree {
    val binarySearchTree = BinarySearchTree()
    val primeNumbers = findNthFirstPrimeNumbers(treeSize.toLong())
    shuffle(primeNumbers)
    primeNumbers.forEach { binarySearchTree.insertNode(BinaryTreeNodeImpl(it, it.toLong())) }

    return binarySearchTree
}

fun findNthFirstPrimeNumbers(limitValue: Long): List<Int> {

    return iterate(BigInteger("2"), BigInteger::nextProbablePrime)
            .limit(limitValue)
            .map(BigInteger::toInt)
            .toList()
}
