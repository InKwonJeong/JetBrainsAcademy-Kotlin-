package calculator

import java.lang.NumberFormatException
import java.math.BigInteger
import java.util.*
import kotlin.math.pow

private val scanner = Scanner(System.`in`)
private var finish = false
private val variables = hashMapOf<String, BigInteger>()

fun main() {
    while (!finish)
        start()
}

fun start() {
    val command = scanner.nextLine()
            .replace("\\s+".toRegex(), "")
            .replace("[+]+".toRegex(), "+")
            .replace("[-]([-][-])*".toRegex(), "-")
            .replace("([-][-])+".toRegex(), "+")

    if (command == "")
        return

    if (command.startsWith('/')) {
        executeCommand(command)
        return
    }

    if (command.contains('=')) {
        assign(command)
        return
    }

    val postfix = makePostfix(command)
    if(postfix == null) {
        println("Invalid expression")
    } else {
        operate(postfix)?.let { println(it) }
    }
}

fun executeCommand(command: String) {
    when (command) {
        "/exit" -> finish()
        "/help" -> println("The program calculates the sum of numbers")
        else -> println("Unknown command")
    }
}

fun finish() {
    println("Bye!")
    finish = true
}

fun assign(command: String) {
    val validExpression = "[0-9a-zA-Z]+=([a-zA-Z]+|[0-9]+)".toRegex()
    val expression = command.split("=")
    if (!validExpression.matches(command))
        println("Invalid assignment")
    else {
        val validId = "[a-zA-Z]+".toRegex()
        val validNumber = "[0-9]+".toRegex()
        if (!validId.matches(expression[0])) {
            println("Invalid identifier")
        } else {
            when {
                validId.matches(expression[1]) -> {
                    variables[expression[1]].let {
                        if (it != null) {
                            variables[expression[0]] = it
                        } else {
                            println("Unknown variable")
                        }
                    }
                }
                validNumber.matches(expression[1]) -> {
                    variables[expression[0]] = BigInteger(expression[1])
                }
                else -> {
                    println("Invalid assignment")
                }
            }
        }
    }
}

fun operate(postfix: String): BigInteger? {
    val elements = postfix.split(" ")
    val nums = Stack<BigInteger>()
    for(element in elements) {
        if(listOf("+", "-", "*", "/", "^").contains(element)) {
            val b = nums.pop()
            val a = nums.pop()
            when(element) {
                "+" -> nums.push(a + b)
                "-" -> nums.push(a - b)
                "*" -> nums.push(a * b)
                "/" -> nums.push(a / b)
                "^" -> nums.push(a.pow(b.intValueExact()))
            }
        } else {
            try {
                val num = BigInteger(element)
                nums.push(num)
            } catch (e: NumberFormatException) {
                variables[element].let {
                    if(it != null) {
                        nums.push(it)
                    } else {
                        println("Unknown variable")
                        return null
                    }
                }
            }
        }
    }
    return nums.pop()
}

fun makePostfix(command: String) : String? {
    val element = "(([-]|[+])?[0-9]+|[a-zA-Z]+)"
    val operator = "([-]|[+]|[*]|[/]|[\\^])"
    val expression = "$element($operator$element)*"
    val withParentheses =
            "[(]($expression|[(]$expression[)])($operator($expression|[(]$expression[)]))*[)]"
    val validExpression =
            "($expression|$withParentheses)($operator($expression|$withParentheses))*".toRegex()

    if(validExpression.matches(command)) {
        val postfix = Stack<String>()
        val operators = Stack<Char>()
        var num = ""
        for(c in command) {
            if(c in '0'..'9' || c in 'a'..'z' || c in 'A'..'Z') {
                num += c
            } else {
                if(num != "") {
                    postfix.push(num)
                    num = ""
                }

                if(postfix.isEmpty()) {
                    num += c
                    continue
                }

                if(!operators.isEmpty()) {
                    when(c) {
                        '+', '-' -> {
                            if(operators.peek() != '(') {
                                val b = postfix.pop()
                                val a = postfix.pop()
                                postfix.push("$a $b ${operators.pop()}")
                            }
                            operators.push(c)
                        }
                        '*', '/' -> {
                            if(!listOf('(' ,'-', '+').contains(operators.peek())) {
                                val b = postfix.pop()
                                val a = postfix.pop()
                                postfix.push("$a $b ${operators.pop()}")
                            }
                            operators.push(c)
                        }
                        '^' -> {
                            if(operators.peek() == '^') {
                                val b = postfix.pop()
                                val a = postfix.pop()
                                postfix.push("$a $b ${operators.pop()}")
                            }
                            operators.push(c)
                        }
                        '(' -> {
                            operators.push(c)
                        }
                        ')' -> {
                            while (operators.peek() != '(') {
                                val b = postfix.pop()
                                val a = postfix.pop()
                                postfix.push("$a $b ${operators.pop()}")
                            }
                            operators.pop()
                        }
                    }
                } else {
                    operators.push(c)
                }
            }
        }

        if(num != "")
            postfix.push(num)

        while(!operators.isEmpty()) {
            val b = postfix.pop()
            val a = postfix.pop()
            postfix.push("$a $b ${operators.pop()}")
        }

        return postfix.pop()
    } else {
        return null
    }
}


