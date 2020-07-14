package datastructures.tree

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class HuffmanTreeTest {

    @Test
    fun `should encode and decode a given word using a huffman tree`() {
        val inputWord = "Huffman coding is a data compression algorithm."
        val huffmanTree = HuffmanTree(inputWord)

        val encoding = huffmanTree.getEncoding()
        val decoding = huffmanTree.getDecoding(encoding)

        assertEquals(194, encoding.length())
        assertEquals(decoding, inputWord)
    }

    @Test
    fun `should encode and decode word of length one`() {
        val inputWord = "A"
        val huffmanTree = HuffmanTree(inputWord)

        val encoding = huffmanTree.getEncoding()
        val decoding = huffmanTree.getDecoding(encoding)

        assertEquals(1, encoding.length())
        assertEquals(decoding, inputWord)
    }

    @Test
    fun `should give empty response when input is empty`() {
        val huffmanTree = HuffmanTree("")

        val encoding = huffmanTree.getEncoding()
        val decoding = huffmanTree.getDecoding(encoding)

        assertTrue(encoding.isEmpty)
        assertTrue(decoding.isEmpty())
    }
}