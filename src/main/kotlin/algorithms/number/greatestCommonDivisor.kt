package algorithms.number

/**
 * [Euclidean Algorithm](https://en.wikipedia.org/wiki/Euclidean_algorithm)
 * for greatest common divisor of a pair of numbers
 *
 * @param a First number
 * @param b Second number
 * @return the greatest common divisor of a and b
 */
fun gcd(a : Long, b : Long) : Long{
    if(b == 0L){
        return a
    }
    return gcd(b, a.rem(b))
}