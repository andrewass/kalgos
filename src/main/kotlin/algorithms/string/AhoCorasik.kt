package algorithms.string

class AhoCorasick {

    val alphaSize = 26
    private val trie = mutableListOf<Vertex>()

    init {
        trie.add(Vertex())
    }

    /**
     *
     */
    fun addString(word : String){
        var curr = 0
        for(char in word){
            val index = char - 'a'
            if(trie[curr].next[index] == -1){
                trie[curr].next[index] = trie.size
                trie.add(Vertex())
            }
            curr = trie[curr].next[index]
        }
        trie[curr].leaf = true
    }

    inner class Vertex(){
        val next = IntArray(alphaSize) {-1}
        var leaf = false
    }
}