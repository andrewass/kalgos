package utils

import kotlin.math.ln


fun log(x : Int) : Int = (ln(x.toDouble()) / ln(2.00)).toInt()

