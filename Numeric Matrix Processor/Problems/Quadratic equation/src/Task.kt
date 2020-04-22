import java.util.Scanner
import kotlin.math.pow
import kotlin.math.sqrt

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    // put your code here
    val a = scanner.nextDouble()
    val b = scanner.nextDouble()
    val c = scanner.nextDouble()

    val a1 = (-b + sqrt(b.pow(2) - 4 * a * c)) / (2 * a)
    val a2 = (-b - sqrt(b.pow(2) - 4 * a * c)) / (2 * a)

    if(a1 > a2)
        println("$a2\n$a1")
    else
        println("$a1\n$a2")
}