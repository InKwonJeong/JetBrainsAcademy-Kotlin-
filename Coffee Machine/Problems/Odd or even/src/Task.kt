import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val a = scanner.nextInt()
    if(a % 2 == 0)
        print("EVEN")
    else
        print("ODD")
}