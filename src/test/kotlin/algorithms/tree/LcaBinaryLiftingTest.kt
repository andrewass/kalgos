package algorithms.tree

import implementations.TreeNodeImpl
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class LcaBinaryLiftingTest {

    private val nodes = mutableListOf<TreeNodeImpl>()
    private lateinit var lcaTree : LcaBinaryLifting

    @BeforeAll
    private fun init() {
        createTree()
        lcaTree = LcaBinaryLifting(nodes, nodes[0])
    }

    @Test
    fun `should find lca where one is parent of the other`(){
        val lca = lcaTree.findLca(nodes[4], nodes[23])
        assertEquals(lca, nodes[4])
    }

    @Test
    fun `should find lca when both nodes are root`(){
        val lca = lcaTree.findLca(nodes[0], nodes[0])
        assertEquals(lca, nodes[0])
    }

    @Test
    fun `should find lca where both nodes are equal`(){
        val lca = lcaTree.findLca(nodes[14], nodes[14])
        assertEquals(lca, nodes[14])
    }

    @Test
    fun `should find lca of two nodes of different depth v1`(){
        val lca = lcaTree.findLca(nodes[25], nodes[11])
        assertEquals(lca, nodes[2])
    }

    @Test
    fun `should find lca of two nodes of different depth v2`(){
        val lca = lcaTree.findLca(nodes[25], nodes[23])
        assertEquals(lca, nodes[0])
    }

    @Test
    fun `should find lca of two nodes of equal depth`(){
        val lca = lcaTree.findLca(nodes[16], nodes[15])
        assertEquals(lca, nodes[0])
    }

    private fun createTree() {
        for (i in 0 until 26) {
            nodes.add(TreeNodeImpl(id = i))
        }

        val rows = javaClass.getResource("/tree/LcaTree").readText().split("\r\n")
        rows.forEach {
            val nodes = it.split(" ")
            addChild(nodes.first().toInt(), nodes.last().toInt())
        }
    }

    private fun addChild(parent: Int, child: Int) {
        nodes[parent].children.add(nodes[child])
        nodes[child].parent = nodes[parent]
    }
}