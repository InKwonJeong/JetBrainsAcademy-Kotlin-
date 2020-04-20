import java.lang.Exception
import java.util.*

fun main(args: Array<String>) {
    // put your code here
    val scanner = Scanner(System.`in`)
    val a = scanner.nextInt()
    val b = scanner.nextInt()

    try {
        val c = a / b
        print(c)
    } catch (e: Exception) {
        println("Division by zero, please fix the second argument!")
    }
}