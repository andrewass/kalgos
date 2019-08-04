package algorithms.graph.shortestpath

/**
 * Implementation of the
 * [Floyd-Warshall algorihtm](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm)
 * for finding shortest path between all pairs of vertices in a given graph. Also able to detect negative cycles.
 *
 * @param distance An array of the vertices in the graph
 * @param hasNegativeEdges Indicating whether the graph contains negative edges or not.
 */
class FloydWarshall(private val distance: Array<DoubleArray>, private val hasNegativeEdges: Boolean = false) {

    init {
        setAllPairsDistances()
        if (hasNegativeEdges) {
            markNegativeCycles()
        }
    }

    /**
     * Get the shortest distance between a pair of vertices.
     *
     * @param from Index of the starting vertex
     * @param to Index of the destination vertex
     * @return a double value holding the distance
     */
    fun distance(from: Int, to: Int) = distance[from][to]

    /**
     * Update the distance between all pairs of vertices.
     */
    private fun setAllPairsDistances() {
        for (k in distance.indices) {
            for (i in distance.indices) {
                for (j in distance.indices) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j]
                    }
                }
            }
        }
    }

    /**
     * Mark distance between a pair of vertices as negative infinity when a
     * negative cycle exists.
     */
    private fun markNegativeCycles() {
        for (k in distance.indices) {
            for (i in distance.indices) {
                for (j in distance.indices) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = Double.NEGATIVE_INFINITY
                    }
                }
            }
        }
    }
}