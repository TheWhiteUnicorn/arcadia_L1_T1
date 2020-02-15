package com.thewhiteunicorn.arcadialearnapp

import java.util.*

fun factorial(num: Int): Int {
    var result = 1
    for (i in 2..num) result *= i
    return result
}


fun main() {
    val items = listOf(1, 2, 3, 4, 5, 6)

    val sequenceLength:Int = items.size
    val variantsCount:Int = factorial(sequenceLength)

    val result = Array(variantsCount) {Array(sequenceLength) {0} }
    val numbersLeftForLine = Array(variantsCount) {LinkedList(items)}

    var verticalSections = 1
    var numbersVariantsPerSection = sequenceLength
    var numberRepeatsOnSectionCount = variantsCount / sequenceLength

    for (columnNo in 0 until sequenceLength) { // Column
        var lineNo = 0
        for (i in 0 until verticalSections) { // Section
            val numbersAvailableForSection = numbersLeftForLine[lineNo].toList()

            for (j in 0 until numbersVariantsPerSection) { // Iterate over numbers
                val numberForSection = numbersAvailableForSection[j]

                for (k in 0 until numberRepeatsOnSectionCount) { // Repeat number
                    numbersLeftForLine[lineNo].remove(numberForSection)
                    result[lineNo][columnNo] = numberForSection
                    lineNo++
                }
            }
        }

        verticalSections *= numbersVariantsPerSection
        numbersVariantsPerSection--
        if (numbersVariantsPerSection > 0)
            numberRepeatsOnSectionCount = variantsCount / verticalSections / numbersVariantsPerSection
    }

    for (array in result) {
        for (value in array) {
            print("$value ")
        }
        println()
    }
}