package algorithms.geometry


fun polygonArea(points : List<Point>) : Double {
    var area = 0.00
    for(i in 0..points.size-2){
        area += (points[i].x * points[i+1].y - points[i+1].x * points[i].y)
    }
    return area / 2
}