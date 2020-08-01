package utils

/**
 * Find the product of all the numbers in the list
 */
fun <E : Number> List<E>.product(): Long {
    if (this.isEmpty()) {
        return 0L
    }
    var product = 1L
    for (numb in this) {
        when (numb) {
            is Long -> product *= numb
            is Int -> product *= numb
        }
    }
    return product
}