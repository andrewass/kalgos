package algorithms.string

/**
 * Implementation of the Knuth-Morris-Pratt algorithm, returning a list of
 * start indices where a given pattern match a given text
 *
 * @param txt the word which may contain multiple matches of a given pattern
 * @param pat the word which may exists as multiple substring in text txt
 * @param caseSensitive decides whether casing should affect equality
 * @return a list containing a list of the starting indices of each match
 */
fun kmpSearch(txt: String, pat: String, caseSensitive: Boolean = true): List<Int> {
    val text = if (caseSensitive) txt else txt.toLowerCase()
    val pattern = if (caseSensitive) pat else pat.toLowerCase()

    val kmpTable = createKmpTable(pattern)
    val indicesList = mutableListOf<Int>()

    var tInd = 0
    var pInd = 0

    while (tInd < text.length) {
        if (pattern[pInd] == text[tInd]) {
            tInd++
            pInd++
            if (pInd == pattern.length) {
                indicesList.add(tInd - pInd)
                pInd = kmpTable[pInd - 1]
            }
        } else {
            if (pInd != 0)
                pInd = kmpTable[pInd - 1];
            else
                tInd++
        }
    }
    return indicesList
}

/**
 * Creates the table KMP uses to decide shifts of pattern.
 * Any cell c stores the length of longest string which is both prefix and suffix
 * in a substring of the word ranging from 0 to cell c.
 *
 * @param word the word used for constructing the table
 * @return an array storing length of longest prefix and suffix for a given index.
 */
private fun createKmpTable(word: String): IntArray {
    var wInd = 1
    var counter = 0
    val table = IntArray(word.length)

    table[0] = 0

    while (wInd < word.length) {
        if (word[wInd] == word[counter]) {
            counter++
            table[wInd] = counter
            wInd++
        } else {
            if (counter != 0) {
                counter = table[counter - 1]
            } else {
                table[wInd] = 0
                wInd++
            }
        }
    }
    return table
}
