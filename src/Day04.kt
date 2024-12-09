fun main() {

    fun isValidGridPosition(pos: Pair<Int, Int>, rows: Int, cols: Int): Boolean {
        val (x, y) = pos
        return x in 0 until rows && y in 0 until cols
    }

    fun part1(input: List<String>): Int {

        var xmasCount = 0
        val rows = input.size
        val columns = input[0].length

        val directions = listOf(
            // First digit is Row change (positive is down, negative is up, 0 is same row
            // Second digit is Column change (positive is right, negative is left, 0 is same column
            Pair(0, 1),  // Right
            Pair(1, 0),  // Down
            Pair(1, 1),  // RightDown
            Pair(1, -1), // LeftDown
            Pair(0, -1), // Left
            Pair(-1, 0), // Up
            Pair(-1, -1),// LeftUp
            Pair(-1, 1)  // RightUp
        )

        for (x in 0 until rows) {
            for (y in 0 until columns) {
                if (input[x][y] == 'X') {
                    // Investigate evry cell in each direction.
                    // Because direction is a pair, you can use .first and .second
                    for (direction in directions) {
                        val directionX = direction.first
                        val directionY = direction.second

                        val possibleM = x + directionX to y + directionY
                        val possibleA = x + 2 * directionX to y + 2 * directionY
                        val possibleS = x + 3 * directionX to y + 3 * directionY

                        if (isValidGridPosition(
                                possibleM,
                                rows,
                                columns
                            ) && input[possibleM.first][possibleM.second] == 'M' &&
                            isValidGridPosition(
                                possibleA,
                                rows,
                                columns
                            ) && input[possibleA.first][possibleA.second] == 'A' &&
                            isValidGridPosition(
                                possibleS,
                                rows,
                                columns
                            ) && input[possibleS.first][possibleS.second] == 'S'
                        ) {
//                            println("XMAS gevonden vanaf positie ($x, $y) in richting (${direction.first}, ${direction.second})")
                            xmasCount++
                        }
                    }
                }
            }

        }

        return xmasCount

    }

    fun part2(input: List<String>): Int {
        var xmasCount = 0
        val rows = input.size
        val columns = input[0].length

        val directions = listOf(
            // First digit is Row change (positive is down, negative is up, 0 is same row
            // Second digit is Column change (positive is right, negative is left, 0 is same column
//            Pair(0, 1),  // Right
//            Pair(1, 0),  // Down
            Pair(1, 1),  // RightDown
            Pair(1, -1), // LeftDown
//            Pair(0, -1), // Left
//            Pair(-1, 0), // Up
            Pair(-1, -1),// LeftUp
            Pair(-1, 1)  // RightUp
        )

        for (x in 0 until rows) {
            for (y in 0 until columns) {
                if (input[x][y] == 'A') {
                    // Investigate evry cell in each direction.
                    // Because direction is a pair, you can use .first and .second
                    var mas = 0
                    for (direction in directions) {
                        val directionX = direction.first
                        val directionY = direction.second

                        val possibleM = x + directionX to y + directionY
                        val possibleS = x + (-1 * directionX) to y + (-1 * directionY)

                        if (isValidGridPosition(
                                possibleM,
                                rows,
                                columns
                            ) && input[possibleM.first][possibleM.second] == 'M' &&
                            isValidGridPosition(
                                possibleS,
                                rows,
                                columns
                            ) && input[possibleS.first][possibleS.second] == 'S'
                        ) {
//                            println("MAS gevonden vanaf positie ($x, $y) in richting (${direction.first}, ${direction.second})")
                            mas++
                        }
                    }
                    if (mas > 1) xmasCount++
                }
            }

        }
        return xmasCount
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
//    check(part1(testInput) == 1)
//    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
