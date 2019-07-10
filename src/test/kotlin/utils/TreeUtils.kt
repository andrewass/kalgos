package utils

import datastructures.tree.BinarySearchTree
import implementations.BinaryTreeNodeImpl
import java.math.BigInteger
import java.util.*
import java.util.stream.Stream
import java.util.stream.Stream.*
import kotlin.streams.toList

fun createBinarySearchTree(): BinarySearchTree {
    val binarySearchTree = BinarySearchTree()

    val primeNumbers = findNthFirstPrimeNumbers(15)
    Collections.shuffle(primeNumbers)
    primeNumbers.forEach { binarySearchTree.insertNode(BinaryTreeNodeImpl(it)) }
    return binarySearchTree
}

fun findNthFirstPrimeNumbers(limitValue: Long): List<Int> {

    return iterate(BigInteger("2"), BigInteger::nextProbablePrime)
            .limit(limitValue)
            .map(BigInteger::toInt)
            .toList()
}
