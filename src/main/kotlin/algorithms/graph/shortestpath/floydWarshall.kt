package algorithms.graph.shortestpath

/**
 * Implementation of the
 * [Floyd-Warshall algorihtm](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm)
 * for finding shortest path between all pairs of vertices in a given graph. Also able to detect negative cycles.
 *
 * @param distance An array of the vertices in the graph
 * @param hasNegativeEdges Indicating whether the graph contains negative edges or not.
 */
fun floydWarshall(distance: Array<DoubleArray>, hasNegativeEdges: Boolean = false) {

    setAllPairsDistances(distance)
    if (hasNegativeEdges) {
        markNegativeCycles(distance)
    }
}


/**
 * Update the distance between all pairs of vertices.
 *
 * @param distance A 2D array storing the distance between any two pairs of vertices
 */
private fun setAllPairsDistances(distance: Array<DoubleArray>) {
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
 *
 * @param distance A 2D array storing the distance between any two pairs of vertices
 */
private fun markNegativeCycles(distance: Array<DoubleArray>) {
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