package algorithms.string

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AhoCorasickTest{

    @Test
    fun test1(){
        val ahoCorasick = AhoCorasick()
        ahoCorasick.addString("randomstring")
        ahoCorasick.addString("ransom")

    }
}