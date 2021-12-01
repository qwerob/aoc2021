import org.junit.jupiter.api.Assertions.assertEquals

fun main() {

    fun part1(input: List<Int>): Int {
        var numberOfIncreases = 0
        input.forEachIndexed { index, s -> if (index > 0 && s > input[index - 1]) numberOfIncreases++ }
        return numberOfIncreases
    }

    fun part2(input: List<Int>): Int {
        var numberOfIncreases = 0
        input.forEachIndexed { index, s -> if (index > 2 && s > input[index - 3]) numberOfIncreases++ }
        return numberOfIncreases
    }

    fun measureNumberOfIncreasesByIncrement(input: List<Int>, increment: Int) =
        input.zip(input.drop(increment)).sumOf { (x, y) -> (if (y > x) 1L else 0) }.toInt()

    fun part11(input: List<Int>): Int {
        val increment = 1
        return measureNumberOfIncreasesByIncrement(input, increment)
    }

    fun part22(input: List<Int>): Int {
        val increment = 3
        return measureNumberOfIncreasesByIncrement(input, increment)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInt("Day01_test")
    assertEquals(7, part1(testInput))
    assertEquals(5, part2(testInput))
    assertEquals(7, part11(testInput))
    assertEquals(5, part22(testInput))

    val input = readInputAsInt("Day01")
    println(part1(input))
    println(part2(input))
}
