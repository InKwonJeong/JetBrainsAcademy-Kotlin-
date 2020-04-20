import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)

    // write your code here
    val n = scanner.nextInt()
    val array = IntArray(n)
    for(i in 0 until n)
        array[i] = scanner.nextInt()
    val m = scanner.nextInt()
    if(array.contains(m))
        print("YES")
    else
        print("NO")
}