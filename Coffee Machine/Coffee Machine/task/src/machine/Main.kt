package machine

import java.util.*

enum class Coffee(val water : Int, val milk: Int, val beans: Int, val money: Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6)
}

class CoffeeMachine {
    private var water = 400
    private var milk = 540
    private var beans = 120
    private var cups = 9
    private var money = 550

    fun showStatus() {
        println("The coffee machine has:")
        println("$water of water")
        println("$milk of milk")
        println("$beans of coffee beans")
        println("$cups of disposable cups")
        println("\$$money of money")
    }

    fun fillResources(water: Int, milk: Int, beans: Int, cups: Int) {
        this.water += water
        this.milk += milk
        this.beans += beans
        this.cups += cups
    }

    fun takeMonkey() : Int {
        return money.also { money = 0 }
    }

    private fun canMakeCoffee(coffee: Coffee) : Boolean {
        return water >= coffee.water
                && milk >= coffee.milk
                && beans >= coffee.beans
                && cups >= 1
    }

    fun makeCoffee(type: Int) : Boolean {
        var finish = false
        when(type) {
            1 -> {
                if(canMakeCoffee(Coffee.ESPRESSO)) {
                    water -= 250
                    beans -= 16
                    money += 4
                    cups -= 1
                    finish = true
                }
            }
            2 -> {
                if(canMakeCoffee(Coffee.LATTE)) {
                    water -= 350
                    milk -= 75
                    beans -= 20
                    money += 7
                    cups -= 1
                    finish = true
                }
            }
            3 -> {
                if(canMakeCoffee(Coffee.CAPPUCCINO)) {
                    water -= 200
                    milk -= 100
                    beans -= 12
                    money += 6
                    cups -= 1
                    finish = true
                }
            }
        }
        return finish
    }
}

fun main() {
    val input = Scanner(System.`in`)

    val coffeeMachine = CoffeeMachine()

    loop@ while (true) {
        println("Write action (buy, fill, take, remaining, exit): ")
        val action = input.next()

        println()
        when (action) {
            "buy" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                when (val option = input.next()) {
                    "back" -> {
                        println()
                        continue@loop
                    }
                    else -> {
                        if(coffeeMachine.makeCoffee(option.toInt()))
                            println("I have enough resources, making you a coffee!")
                        else
                            println("Sorry, not enough water!")
                    }
                }
            }
            "fill" -> {
                println("Write how many ml of water do you want to add: ")
                val water = input.nextInt()
                println("Write how many ml of milk do you want to add: ")
                val milk = input.nextInt()
                println("Write how many grams of coffee beans do you want to add: ")
                val beans = input.nextInt()
                println("Write how many disposable cups of coffee do you want to add: ")
                val cups = input.nextInt()
                coffeeMachine.fillResources(water, milk, beans, cups)
            }
            "take" -> {
                println("I gave you \$${coffeeMachine.takeMonkey()}")
            }
            "remaining" -> {
                coffeeMachine.showStatus()
            }
            "exit" -> {
                break@loop
            }
        }

        println()
    }
}
