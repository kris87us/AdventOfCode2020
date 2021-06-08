package adventofcode2020

import adventofcode2020.util.readFileIntoList
import kotlin.math.*

private val inputLines = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/12.txt")
fun main() {
    val position = inputLines
        .map { Instruction(Action.from(it[0]), it.drop(1).toInt()) }
        .fold(Position(0, 0, Action.Cardinal.East, 10, 1)) { acc, instruction -> println(acc); instruction.move(acc) }
    println(position)
    println(position.manhattanDistance())
}

data class Position(val x: Int, val y: Int, val facing: Action.Cardinal, val waypointX: Int, val waypointY: Int) {
    init {
        check(facing in setOf(Action.Cardinal.North, Action.Cardinal.South, Action.Cardinal.East, Action.Cardinal.West)) { "Can't face $facing" }
    }

    fun manhattanDistance() = x.absoluteValue + y.absoluteValue
}

data class Instruction(val action: Action, val count: Int) {
    fun move(currentPos: Position) = action.move(currentPos, count)
}
sealed class Action(val symbol: Char) {
    abstract fun move(currentPos: Position, count: Int): Position
    sealed class Turn(symbol: Char) : Action(symbol) {
        object Left : Turn('L') {
            override fun move(currentPos: Position, count: Int): Position  = calculateRotation(currentPos, count)
        }

        object Right : Turn('R') {
            override fun move(currentPos: Position, count: Int): Position = calculateRotation(currentPos, -count)
        }
        fun calculateRotation(currentPos: Position, count: Int): Position {
            val x = currentPos.waypointX.toDouble()
            val y = currentPos.waypointY.toDouble()
            val r = sqrt(x.pow(2) + y.pow(2))
            val θ = atan2(y, x)
            val countRad = (count / 360.0) * 2.0 * PI
            val newθ = θ + countRad
            return currentPos.copy(waypointX = (r * cos(newθ)).roundToInt(), waypointY = (r * sin(newθ)).roundToInt())
        }
    }

    sealed class Cardinal(symbol: Char) : Action(symbol) {

        object North : Cardinal('N') {
            override fun move(currentPos: Position, count: Int): Position = currentPos.copy(waypointY= currentPos.waypointY + count)
        }
        object South : Cardinal('S') {
            override fun move(currentPos: Position, count: Int): Position = currentPos.copy(waypointY= currentPos.waypointY - count)
        }
        object East : Cardinal('E') {
            override fun move(currentPos: Position, count: Int): Position = currentPos.copy(waypointX= currentPos.waypointX + count)
        }
        object West : Cardinal('W') {
            override fun move(currentPos: Position, count: Int): Position = currentPos.copy(waypointX= currentPos.waypointX - count)
        }
    }

    object Forward : Action('F') {
        override fun move(currentPos: Position, count: Int): Position {
            var position = currentPos
            for (i in 0 until count) {
                position = position.copy(x = position.x + position.waypointX, y = position.y + position.waypointY)
            }
            return position
        }
    }

    companion object {
        fun from(char: Char) = listOf(
            Forward,
            Turn.Left,
            Turn.Right,
            Cardinal.North,
            Cardinal.South,
            Cardinal.East,
            Cardinal.West,
        ).map { it.symbol to it }.toMap()[char] ?: throw IllegalArgumentException("unknown Action: $char")
    }
}