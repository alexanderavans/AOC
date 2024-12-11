enum class Direction(val deltaRow: Int, val deltaCol: Int) {
    UP(-1, 0),
    RIGHT(0, 1),
    DOWN(1, 0),
    LEFT(0, -1);

    fun turnClockwise(): Direction = when (this) {
        UP -> RIGHT
        RIGHT -> DOWN
        DOWN -> LEFT
        LEFT -> UP
    }
}

fun findStartingLocationAndDirection(grid: List<String>): Pair<Pair<Int, Int>, Direction>? {
    for (row in grid.indices) {
        for (col in grid[row].indices) {
            when (grid[row][col]) {
                '<' -> return Pair(row to col, Direction.LEFT)
                '>' -> return Pair(row to col, Direction.RIGHT)
                '^' -> return Pair(row to col, Direction.UP)
                'V' -> return Pair(row to col, Direction.DOWN)
            }
        }
    }
    return null
}

fun followPath(
    grid: MutableList<MutableList<Char>>,
    startLocation: Pair<Int, Int>,
    startDirection: Direction
): Int {
    var currentRow = startLocation.first
    var currentCol = startLocation.second
    var direction = startDirection
    var markedCount = 0

    grid[currentRow][currentCol] = 'X'
    markedCount++

    while (true) {
        val nextRow = currentRow + direction.deltaRow
        val nextCol = currentCol + direction.deltaCol

        if (nextRow !in grid.indices || nextCol !in grid[nextRow].indices) {
            break // Outside the grid
        }

        when (grid[nextRow][nextCol]) {
            '#' -> direction = direction.turnClockwise()
            else -> {
                currentRow = nextRow
                currentCol = nextCol
                if (grid[currentRow][currentCol] != 'X') {
                    grid[currentRow][currentCol] = 'X'
                    markedCount++
                }
            }
        }
    }

    return markedCount
}

fun detectLoops(
    grid: MutableList<MutableList<Char>>,
    startLocation: Pair<Int, Int>,
    startDirection: Direction
): Int {
    var loops = 0

    for (row in grid.indices) {
        for (col in grid[row].indices) {
            if (grid[row][col] == 'X') {
                grid[row][col] = '0' // Temporary wall

                val visited = mutableSetOf<Pair<Pair<Int, Int>, Direction>>()
                var loopRow = startLocation.first
                var loopCol = startLocation.second
                var loopDirection = startDirection

                while (true) {
                    if (Pair(Pair(loopRow, loopCol), loopDirection) in visited) {
                        loops++
                        break
                    }

                    visited.add(Pair(Pair(loopRow, loopCol), loopDirection))

                    val nextRow = loopRow + loopDirection.deltaRow
                    val nextCol = loopCol + loopDirection.deltaCol

                    if (nextRow !in grid.indices || nextCol !in grid[nextRow].indices) {
                        break // BOutside the grid
                    }

                    when (grid[nextRow][nextCol]) {
                        '#' -> loopDirection = loopDirection.turnClockwise()
                        '0' -> loopDirection = loopDirection.turnClockwise()
                        else -> {
                            loopRow = nextRow
                            loopCol = nextCol
                        }
                    }
                }

                grid[row][col] = 'X' // Recover original setting
            }
        }
    }

    return loops
}

fun main() {
    fun part1(input: List<String>): Int {
        val startInfo = findStartingLocationAndDirection(input) ?: Pair(Pair(0, 0), Direction.RIGHT)
        val (startLocation, startDirection) = startInfo
        val grid = input.map { it.toCharArray().toMutableList() }.toMutableList()

        return followPath(grid, startLocation, startDirection)
    }

    fun part2(input: List<String>): Int {
        val startInfo = findStartingLocationAndDirection(input) ?: Pair(Pair(0, 0), Direction.RIGHT)
        val (startLocation, startDirection) = startInfo
        val grid = input.map { it.toCharArray().toMutableList() }.toMutableList()

        followPath(grid, startLocation, startDirection) // Markeer de route
        return detectLoops(grid, startLocation, startDirection)
    }



    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day06_test")
//    part1(testInput).println()
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()

}
