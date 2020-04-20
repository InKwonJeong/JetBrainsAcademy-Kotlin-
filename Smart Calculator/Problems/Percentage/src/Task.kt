import java.math.BigInteger
import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val a = BigInteger(scanner.next())
    val b = BigInteger(scanner.next())
    val hundred = BigInteger.valueOf(100)
    val c = a + b
    print("${a * hundred / c}% ${b * hundred / c}%")
}