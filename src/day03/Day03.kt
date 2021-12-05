package day03

import org.junit.jupiter.api.Assertions
import readInputByDay
import readTestInputByDay

fun main() {

    fun getMostCommonBinary(s: String) = if (s.count { it == '0' } > s.count()/2 ) 0 else 1

    fun transformRowsToColumns(input: List<String>): MutableList<String> {
        val amountOfLists = input.first().count()
        val listOfColumns = MutableList(amountOfLists) {""}
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

    val testInput = readTestInputByDay(3)
    Assertions.assertEquals(198, part1(testInput))
    Assertions.assertEquals(1, getMostCommonBinary("01110"))
    Assertions.assertEquals("10110", getGammaAsBinary(testInput))
    Assertions.assertEquals("01001", getEpsilonByGamma(getGammaAsBinary(testInput)))

    val input = readInputByDay(3)
    println("part1: " + part1(input))
}

