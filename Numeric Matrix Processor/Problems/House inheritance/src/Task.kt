fun main() {
    val rooms = readLine()!!
    val price = readLine()!!
    val house = create(rooms.toInt(), price.toInt())
    println(totalPrice(house))
}

fun totalPrice(house: House): Int {
    return (house.price * house.coefficient).toInt()
}

fun create(rooms: Int, price: Int): House {
    val p = when {
        price < 0 -> 0
        price > 1_000_000 -> 1_000_000
        else -> price
    }

    return when {
        rooms <= 1 -> Cabin(rooms, p)
        rooms <= 3 -> Bungalow(rooms, p)
        rooms <= 4 -> Cottage(rooms, p)
        rooms <= 7 -> Mansion(rooms, p)
        else -> Palace(rooms, p)
    }
}

open class House(val rooms: Int, val price: Int) {
    open val coefficient = 0.0
}

class Cabin(rooms: Int, price: Int) : House(rooms, price) {
    override val coefficient = 1.0
}

class Bungalow(rooms: Int, price: Int) : House(rooms, price) {
    override val coefficient = 1.2
}

class Cottage(rooms: Int, price: Int) : House(rooms, price) {
    override val coefficient = 1.25
}

class Mansion(rooms: Int, price: Int) : House(rooms, price) {
    override val coefficient = 1.4
}

class Palace(rooms: Int, price: Int) : House(rooms, price) {
    override val coefficient = 1.6
}
