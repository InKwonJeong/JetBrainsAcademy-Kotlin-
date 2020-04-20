fun main() {
    val numbers = IntArray(100)
    numbers[0] = 1
    numbers[9] = 10
    numbers[99] = 100

    println(numbers.joinToString())
}