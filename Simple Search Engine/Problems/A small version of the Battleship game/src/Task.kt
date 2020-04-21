import java.util.Scanner

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    // put your code here
    val X = mutableListOf(1, 2, 3, 4, 5)
    val Y = mutableListOf(1, 2, 3, 4, 5)
    for(i in 1..3) {
        X.remove(input.nextInt())
        Y.remove(input.nextInt())
    }

    for((i, x) in X.withIndex()) {
        if(i == X.lastIndex)
            print(x)
        else
            print("$x ")
    }

    println()

    for((i, y) in Y.withIndex()) {
        if(i == Y.lastIndex)
            print(y)
        else
            print("$y ")
    }
}