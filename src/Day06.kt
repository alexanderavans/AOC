fun main() {

    fun part1(input: List<String>): Int {
        val rows = input.size
        val columns = input[0].length

        return input.size
    }

    fun part2(input: List<String>): Int {

        return input.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day06_test")
    part1(testInput).println()
    part2(testInput).println()

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day06")
//    part1(input).println()
//    part2(input).println()
}
