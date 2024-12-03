fun main() {

    fun part1(input: List<String>): Int {

        val completeLine = input.joinToString("")
        val regex = Regex("mul\\(\\d+,\\d+\\)")
        val matches = regex.findAll(completeLine).map { it.value }.toList()

        var result = 0

        for (item in matches) {
            val numbers = item.substringAfter("mul(").substringBefore(")").split(",")
            val product = numbers[0].toInt() * numbers[1].toInt()
            result += product
        }

        return result

    }

    fun part2(input: List<String>): Int {

        val completeLine = input.joinToString("")
        val regex = Regex("(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don't\\(\\))")
        val matches = regex.findAll(completeLine).map { it.value }.toList()

        var result = 0

        var shouldMultiply = false
        for (item in matches) {
            when {
                item.startsWith("do()") -> shouldMultiply = true
                item.startsWith("don't()") -> shouldMultiply = false
                item.startsWith("mul") && shouldMultiply -> {
                    val numbers = item.substringAfter("mul(").substringBefore(")").split(",")
                    val product = numbers[0].toInt() * numbers[1].toInt()
                    result += product
                }
            }
        }
        return result

    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
