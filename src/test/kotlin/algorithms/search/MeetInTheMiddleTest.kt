package algorithms.search

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MeetInTheMiddleTest {

    private var modVal = 0L
    private var expectedValue = 0L
    private val numberList = mutableListOf<Long>()

    @Test
    fun `should find max sequence sum from input file 1`() {
        val rows = javaClass.getResource("/search/Meetinthemiddle1").readText().split("\r\n")
        prepareTestCase(rows)
        assertEquals(expectedValue, meetInTheMiddle(numberList, modVal))
    }

    @Test
    fun `should find max sequence sum from input file 2`() {
        val rows = javaClass.getResource("/search/Meetinthemiddle2").readText().split("\r\n")
        prepareTestCase(rows)
        assertEquals(expectedValue, meetInTheMiddle(numberList, modVal))
    }

    @Test
    fun `should find max sequence sum from input file 3`() {
        val rows = javaClass.getResource("/search/Meetinthemiddle3").readText().split("\r\n")
        prepareTestCase(rows)
        assertEquals(expectedValue, meetInTheMiddle(numberList, modVal))
    }

    @Test
    fun `should find max sequence sum from input file 4`() {
        val rows = javaClass.getResource("/search/Meetinthemiddle4").readText().split("\r\n")
        prepareTestCase(rows)
        assertEquals(expectedValue, meetInTheMiddle(numberList, modVal))
    }

    private fun prepareTestCase(rows: List<String>) {
        numberList.clear()
        val firstRow = rows.first().split(" ")
        modVal = firstRow.last().toLong()
        val numbers = rows[1].split(" ")
        for (number in numbers) {
            numberList.add(number.toLong())
        }
        expectedValue = rows[2].toLong()
    }
}