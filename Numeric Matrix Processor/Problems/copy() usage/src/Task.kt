import java.util.Scanner

// do not change this data class
data class Box(val height: Int, val length: Int, val width: Int)

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    // implement your code here
    val h = input.nextInt()
    val l1 = input.nextInt()
    val l2 = input.nextInt()
    val w = input.nextInt()

    val box1 = Box(h, l1, w)
    val box2 = box1.copy(length = l2)

    println(box1)
    println(box2)
}