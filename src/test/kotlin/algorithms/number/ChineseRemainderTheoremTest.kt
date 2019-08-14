package algorithms.number

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ChineseRemainderTheoremTest{

    @Test
    fun shouldReturnCorrectResult(){
        val values = listOf(151L, 57L)
        val mods = listOf(783L, 278L)

        assertEquals(31471L, crm(values, mods))
    }
}