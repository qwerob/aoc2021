package day03

import org.junit.jupiter.api.Assertions
import readInputByDay
import readTestInputByDay

fun main() {

    fun getMostCommonBinary(s: String) = if (s.count { it == '0' } > s.count() / 2) 0 else 1

    fun transformRowsToColumns(input: List<String>): List<String> {
        val amountOfLists = input.first().count()
        val listOfColumns = MutableList(amountOfLists) { "" }
        input.forEach { s ->
            s.forEachIndexed { index, singleChar ->
                listOfColumns[index] = listOfColumns[index].plus(singleChar)
            }
        }
        return listOfColumns
    }

    fun getGammaAsBinary(input: List<String>): String {
        val columns = transformRowsToColumns(input)
        var gamma = ""
        columns.forEach {
            gamma = gamma.plus(getMostCommonBinary(it))
        }
        return gamma
    }

    fun getEpsilonByGamma(gamma: String): String {
        var epsilon = ""
        gamma.forEach { epsilon = if (it.toString() == "1") epsilon.plus("0") else epsilon.plus("1") }
        return epsilon
    }

    fun part1(input: List<String>): Int {
        val gamma = getGammaAsBinary(input)
        val epsilon = getEpsilonByGamma(gamma)
        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun whoHasTheLongest(
        firstList: List<String>,
        secondList: List<String>,
        sizeDoesNotMatter: Boolean,
    ): List<String> {
        return if (sizeDoesNotMatter) {
            if (firstList.count() < secondList.count()) firstList else secondList
        } else if (firstList.count() >= secondList.count()) firstList else secondList
    }

    fun partitionByDigitAndReturnTheLongestList(
        input: List<String>,
        digit: Int,
        sizeDoesNotMatter: Boolean,
    ): List<String> {
        val (startsWithOne, startsWithZero) = input.partition { it[digit] == '1' }
        return whoHasTheLongest(startsWithOne, startsWithZero, sizeDoesNotMatter)
    }

    fun getOxygenOrCo2(input: List<String>, sizeDoesNotMatter: Boolean = true): Int {
        var oxygen = input
        var i = 0
        while (oxygen.distinct().count() > 1) {
            oxygen = partitionByDigitAndReturnTheLongestList(oxygen, i, sizeDoesNotMatter)
            i++
        }
        return oxygen.first().toInt(2)
    }

    fun part2(input: List<String>): Int {
        val oxygen = getOxygenOrCo2(input, false)
        val co2 = getOxygenOrCo2(input)
        return oxygen * co2
    }

    val testInput = readTestInputByDay(3)
    Assertions.assertEquals(198, part1(testInput))
    Assertions.assertEquals(1, getMostCommonBinary("01110"))
    Assertions.assertEquals("10110", getGammaAsBinary(testInput))
    Assertions.assertEquals("01001", getEpsilonByGamma(getGammaAsBinary(testInput)))

    Assertions.assertEquals(23, getOxygenOrCo2(testInput, false), "oxygen not correct")
    Assertions.assertEquals(10, getOxygenOrCo2(testInput), "co2 not correct")


    val input = readInputByDay(3)
    println("part1: " + part1(input))
    println("part2: " + part2(input))
}
