package adventofcode2020

import adventofcode2020.util.readFileIntoList

private val seats =
    readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/11.txt").map { it.toList() }

fun countOccupiedSeats(): Int {
    var seatsBefore: List<List<Char>>
    var seatsAfter =  listOf<List<Char>>()
    do {
        seatsBefore = seatsAfter.ifEmpty { seats }
        seatsAfter = seatsBefore.mapIndexed { y, row ->
            row.mapIndexed { x, seat ->
                val occupiedAdjacentSeats = occupiedAdjacentSeats(seatsBefore, y, x)
                when {
                    seat == '.' -> '.'
                    occupiedAdjacentSeats.isEmpty() -> '#'
                    occupiedAdjacentSeats.size >= 5 -> 'L'
                    else -> seat
                }
            }
        }
    } while (seatsBefore != seatsAfter)
    return seatsAfter.flatten().filter { it == '#' }.size
}

private fun occupiedAdjacentSeats(
    seats: List<List<Char>>,
    y: Int,
    x: Int,
    seat: Char
): List<Char> {
    return seats
        .filterIndexed { y1, _ -> y1 in y - 1..y + 1 }
        .flatMap { it.filterIndexed { x1, _ -> x1 in x - 1..x + 1 } }
        .minus(seat)
        .filter { it == '#' }
}

private fun occupiedAdjacentSeats(
    seats: List<List<Char>>,
    y: Int,
    x: Int
): List<Char> {
    val directions = listOf(
        Pair(0, -1),
        Pair(0, +1),
        Pair(-1, 0),
        Pair(-1, +1),
        Pair(-1, -1),
        Pair(+1, 0),
        Pair(+1, +1),
        Pair(+1, -1)
    )
    val adjacentSeats = directions.mapNotNull { direction ->
        var x1 = x
        var y1 = y
        var adjacentSeat: Char? = '.'
        do {
            y1 += direction.first
            x1 += direction.second
            adjacentSeat = seats.getOrNull(y1)?.getOrNull(x1)
        } while (adjacentSeat == '.')
        adjacentSeat
    }

    return adjacentSeats
        .filter { it == '#' }
}

fun main() {
    println(countOccupiedSeats())
}