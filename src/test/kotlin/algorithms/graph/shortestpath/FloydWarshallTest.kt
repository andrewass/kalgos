package algorithms.graph.shortestpath

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FloydWarshallTest{

    @Test
    fun shouldFindCorrectMinimumDistancesBetweenPairsOfnodes(){
        val graph = constructGraph()

        floydWarshall(distance = graph, hasNegativeEdges = true)

        assertEquals(-3.00,graph[0][7])
        assertEquals(Double.POSITIVE_INFINITY,graph[7][0])
        assertEquals(Double.NEGATIVE_INFINITY,graph[0][5])
        assertEquals(Double.POSITIVE_INFINITY,graph[5][0])
        assertEquals(Double.NEGATIVE_INFINITY,graph[3][4])
        assertEquals(Double.NEGATIVE_INFINITY,graph[4][3])
        assertEquals(0.00,graph[2][2])
        assertEquals(Double.POSITIVE_INFINITY,graph[0][6])
    }

    private fun constructGraph(): Array<DoubleArray> {
        val n = 8
        val graph = Array(n){ DoubleArray(n) {Double.POSITIVE_INFINITY}}
        for(i in graph.indices){
            graph[i][i] = 0.00
        }
        graph[0][1] = 5.00
        graph[0][2] = 5.00
        graph[2][7] = -8.00
        graph[0][3] = 100.00
        graph[3][4] = 99.00
        graph[4][3] = -100.00
        graph[4][5] = 100.00

        return graph
    }
}