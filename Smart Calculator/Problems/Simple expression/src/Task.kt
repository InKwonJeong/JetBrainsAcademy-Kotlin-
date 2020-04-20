import java.math.BigInteger
import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val a = BigInteger(scanner.next())
    val b = BigInteger(scanner.next())
    val c = BigInteger(scanner.next())
    val d = BigInteger(scanner.next())

    print(-a * b + c - d)
}