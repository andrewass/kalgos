package algorithms.string

/**
 * Implementation of the Knuth-Morris-Pratt algorithm, returning a list of
 * start indices where a given pattern match a given text
 *
 * @param text the word which may contain multiple matches of a given pattern
 * @param pattern the word which may exists as multiple substring in text text
 * @param caseSensitive decides whether casing should affect equality
 * @return a list containing a list of the starting indices of each match
 */
fun kmpSearch(text: String, pattern: String, caseSensitive: Boolean = true): List<Int> {
    val txt = if (caseSensitive) text else text.toLowerCase()
    val pat = if (caseSensitive) pattern else pattern.toLowerCase()
    val prefixTable = createPrefixPattern(pat)
    val indicesList = mutableListOf<Int>()
    var indT = 0
    var indP = 0

    while (indT < txt.length) {
        if (pat[indP] == txt[indT]) {
            indT++
            indP++
            if (indP == pat.length) {
                indicesList.add(indT - indP)
                indP = prefixTable[indP - 1]
            }
        } else {
            if (indP != 0)
                indP = prefixTable[indP - 1]
            else
                indT++
        }
    }
    return indicesList
}

/**
 * Creates the table KMP algorithm uses to decide shifts of pattern.
 * Any cell c stores the length of longest string which is both prefix and suffix
 * in a substring of the word ranging from 0 to cell c.
 *
 * @param word the word used for constructing the table
 * @return an array storing length of longest prefix and suffix for a given index.
 */
private fun createPrefixPattern(word: String): IntArray {
    var indW = 1
    var counter = 0
    val table = IntArray(word.length)
    table[0] = 0

    while (indW < word.length) {
        if (word[indW] == word[counter]) {
            counter++
            table[indW] = counter
            indW++
        } else {
            if (counter != 0) {
                counter = table[counter - 1]
            } else {
                table[indW] = 0
                indW++
            }
        }
    }
    return table
}
