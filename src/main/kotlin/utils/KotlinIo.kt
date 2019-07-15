package utils

import java.io.*
import java.util.*

/**
 * IO class with exposing various input operations. Kotlin Translation of
 * [Kattio.java](https://github.com/Kattis/kattio/blob/master/Kattio.java)
 */
class KotlinIo(val input: InputStream, val outPut: OutputStream) : PrintWriter(BufferedOutputStream(outPut)) {

    private var reader: BufferedReader = BufferedReader(InputStreamReader(input))
    private var line: String? = null
    private var tokenizer: StringTokenizer? = null
    private var token: String? = null

    fun hasMoreTokens() = peekToken() != null

    fun getInt() = nextToken()!!.toInt()

    fun getDouble() = nextToken()!!.toDouble()

    fun getLong() = nextToken()!!.toLong()

    fun getWord() = nextToken()

    fun getLine(): String? {
        try {
            return reader.readLine()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun peekToken(): String? {
        if (token == null) {
            try {
                while (tokenizer == null || !tokenizer!!.hasMoreTokens()) {
                    line = reader.readLine()
                    if (line == null) {
                        return null
                    }
                    tokenizer = StringTokenizer(line)
                }
                token = tokenizer!!.nextToken()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return token
    }

    private fun nextToken(): String? {
        val ans = peekToken()
        token = null
        return ans
    }
}