package algorithms.rangequery

import entities.Query
import java.util.Collections.sort

fun mosAlgorithm(queries: List<Query>) : List<Int> {
    sort(queries)
    val answers = mutableListOf<Int>(queries.size)

    var currLeft = 0
    var currRight = -1

    //Process each query
    for(query in queries){

        //Increase left side of the windows
        while(currLeft >  query.left){
            currLeft--
        }

        // Increase right side of the window
        while(currRight < query.right){
            currRight++
        }

        // Decrease left side of the window
        while (currLeft < query.left){
            currLeft++
        }

        //Decrease right side of the windows
        while(currRight > query.right){
            currRight--
        }

    }
    return answers
}