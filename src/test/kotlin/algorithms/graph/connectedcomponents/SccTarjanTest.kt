package algorithms.graph.connectedcomponents

import implementations.GraphNodeImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SccTarjanTest {

    @Test
    fun componentCountShouldBeEqualToNodeCountWhenNoEdgesExists() {
        val nodes = mutableListOf<GraphNodeImpl>()
        for (i in 1..6) {
            nodes.add(GraphNodeImpl(i))
        }
        val sccTarjan = SccTarjan(nodes)
        val components = sccTarjan.getStronglyConnectedComponents()

        assertEquals(6, components.size)
    }

    @Test
    fun shouldReturnSingleComponentWhenAllNodesAreWithinSameCycle() {
        val nodes = mutableListOf<GraphNodeImpl>()
        for (i in 0..5) {
            nodes.add(GraphNodeImpl(i))
        }
        for (i in 0..5) {
            nodes[i].addNeighbour(nodes[(i + 1).rem(6)])
        }
        val sccTarjan = SccTarjan(nodes)
        val components = sccTarjan.getStronglyConnectedComponents()

        assertEquals(1, components.size)
    }

    @Test
    fun shouldDivideGraphIntoVariousComponents(){
        val nodes = mutableListOf<GraphNodeImpl>()
        for (i in 0..10) {
            nodes.add(GraphNodeImpl(i))
        }

        nodes[0].addNeighbour(nodes[1])
        nodes[1].addNeighbour(nodes[2])
        nodes[2].addNeighbour(nodes[3])
        nodes[3].addNeighbour(nodes[0])

        nodes[4].addNeighbour(nodes[3])
        nodes[4].addNeighbour(nodes[5])
        nodes[5].addNeighbour(nodes[6])
        nodes[6].addNeighbour(nodes[4])

        nodes[7].addNeighbour(nodes[0])
        nodes[7].addNeighbour(nodes[8])
        nodes[8].addNeighbour(nodes[9])
        nodes[9].addNeighbour(nodes[7])

        val sccTarjan = SccTarjan(nodes)
        val components = sccTarjan.getStronglyConnectedComponents()

        assertEquals(4, components.size)
    }
}