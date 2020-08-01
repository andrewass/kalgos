package utils

/**
 * Convert a binary string to an int
 */
fun String.binaryStringToInt(): Int {
    var sum = 0
    var multiplier = 1
    for (i in this.length - 1 downTo 0) {
        if (this[i] == '1') {
            sum += multiplier
        }
        multiplier += multiplier
    }
    return sum
}