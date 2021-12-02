package day02

import org.junit.jupiter.api.Assertions
import readInputByDay
import readTestInputByDay
import java.lang.IllegalArgumentException

fun main() {

    fun commandToDigit(command: String) = command.filter { it.isDigit() }.toInt()

    fun part1(input: List<String>): Int {
        val pos = Position()
        input.forEach { command ->
            when {
                command.contains("forward") -> pos.horizontal = pos.horizontal + commandToDigit(command)
                command.contains("down") -> pos.depth = pos.depth + commandToDigit(command)
                command.contains("up") -> pos.depth = pos.depth - commandToDigit(command)
                else -> throw IllegalArgumentException("command not known")
            }
        }
        return pos.depth * pos.horizontal
    }

    fun forwardAction(command: String, pos: PositionWithAim): PositionWithAim {
        val forward = commandToDigit(command)
        pos.horizontal = pos.horizontal + forward
        pos.depth = pos.depth + pos.aim * forward
        return pos
    }

    fun downAction(command: String, pos: PositionWithAim): PositionWithAim {
        pos.aim = pos.aim + commandToDigit(command)
        return pos
    }

    fun upAction(command: String, pos: PositionWithAim): PositionWithAim {
        pos.aim = pos.aim - commandToDigit(command)
        return pos
    }

    fun part2(input: List<String>): Int {
        var pos = PositionWithAim()
        input.forEach { command ->
            pos = when {
                command.contains("forward") -> forwardAction(command, pos)
                command.contains("down") -> downAction(command, pos)
                command.contains("up") -> upAction(command, pos)
                else -> throw IllegalArgumentException("command not known")
            }
        }
        return pos.depth * pos.horizontal
    }


    val testInput = readTestInputByDay(2)
    Assertions.assertEquals(150, part1(testInput))
    Assertions.assertEquals(900, part2(testInput))

    val input = readInputByDay(2)
    println("part1: " + part1(input))
    println("part2: " + part2(input))

}

class Position(
    var horizontal: Int = 0,
    var depth: Int = 0
)

class PositionWithAim(
    var horizontal: Int = 0,
    var depth: Int = 0,
    var aim: Int = 0
)
