import java.util.*
import kotlin.math.cos
import kotlin.math.sin

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // write your code here
    val a = scanner.nextDouble()
    println(sin(a) - cos(a))
}