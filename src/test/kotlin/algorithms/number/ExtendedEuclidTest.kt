package algorithms.number

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtendedEuclidTest {

    @Test
    fun shouldReturnCorrectValuesInResultArray() {
        var result = extendedEuclid(180, 150)
        assertEquals(result[0], 30)
        assertEquals(result[1], 1)
        assertEquals(result[2], -1)

        result = extendedEuclid(2132134, 234)
        assertEquals(result[0], 2)
        assertEquals(result[1], -19)
        assertEquals(result[2], 173122)

        result = extendedEuclid(100, 100)
        assertEquals(result[0], 100)
        assertEquals(result[1], 0)
        assertEquals(result[2], 1)

        result = extendedEuclid(100, 100)
        assertEquals(result[0], 100)
        assertEquals(result[1], 0)
        assertEquals(result[2], 1)


        result = extendedEuclid(216364576, 4564568)
        assertEquals(result[0], 136)
        assertEquals(result[1], 9686)
        assertEquals(result[2], -459125)
    }

}