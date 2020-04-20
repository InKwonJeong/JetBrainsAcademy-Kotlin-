import java.math.BigInteger
import java.util.*

fun main() {
    // write your code here
    val scanner = Scanner(System.`in`)
    val a = BigInteger(scanner.next())
    val b = BigInteger(scanner.next())
    val c = BigInteger(scanner.next())

    if(a == b && b == c)
        print(3)
    else if(a != b && b != c && a != c)
        print(0)
    else
        print(2)
}