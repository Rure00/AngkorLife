package com.rure.angkorlife.presentation.utils

import java.text.DecimalFormat

fun getDecimalFormat(number: String): String {
    val decimalFormat = DecimalFormat("#,###")
    val numberValue = number.toIntOrNull() ?: 0
    return decimalFormat.format(numberValue)
}

fun getDecimalFormat(number: Int): String {
    return getDecimalFormat(number.toString())
}