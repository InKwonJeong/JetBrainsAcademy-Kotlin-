package processor

import java.util.*
import kotlin.math.pow

private val scanner = Scanner(System.`in`)
private var finish = false

fun main() {
    while (!finish)
        showMenu()
}

fun showMenu() {
    println("1. Add matrices")
    println("2. Multiply matrix to a constant")
    println("3. Multiply matrices")
    println("4. Transpose matrix")
    println("5. Calculate a determinant")
    println("6. Inverse matrix")
    println("0. Exit")
    print("Your choice: ")
    when (scanner.nextInt()) {
        1 -> {
            val result = addMatrices()
            if (result == null)
                println("Error")
            else {
                println("The addition result is:")
                printMatrix(result)
            }
        }
        2 -> {
            val result = multiplyMatrixToConstant()
            if (result == null)
                println("Error")
            else {
                println("The multiplication result is:")
                printMatrix(result)
            }
        }
        3 -> {
            val result = multiplyMatrices()
            if (result == null)
                println("Error")
            else {
                println("The multiplication result is:")
                printMatrix(result)
            }
        }
        4 -> {
            val result = transposeMatrix()
            if (result == null)
                println("Error")
            else {
                println("The result is:")
                printMatrix(result)
            }
        }
        5 -> calculateDeterminant()
        6 -> inverseMatrix()
        0 -> exit()
    }
}

fun printMatrix(matrix: Array<DoubleArray>) {
    for (row in matrix) {
        for (column in row) {
            print("$column ")
        }
        println()
    }
    println()
}

fun addMatrices(): Array<DoubleArray>? {
    print("Enter size of first matrix: ")
    val row1 = scanner.nextInt()
    val column1 = scanner.nextInt()

    println("Enter first matrix:")
    val matrix1 = Array(row1) { DoubleArray(column1) }
    for (i in 0 until row1) {
        for (j in 0 until column1) {
            matrix1[i][j] = scanner.nextDouble()
        }
    }

    print("Enter size of second matrix: ")
    val row2 = scanner.nextInt()
    val column2 = scanner.nextInt()

    println("Enter second matrix:")
    val matrix2 = Array(row2) { DoubleArray(column2) }
    for (i in 0 until row2) {
        for (j in 0 until column2) {
            matrix2[i][j] = scanner.nextDouble()
        }
    }

    return if (row1 != row2 || column1 != column2)
        null
    else {
        val result = Array(row1) { DoubleArray(column1) }
        for (i in 0 until row1) {
            for (j in 0 until column1)
                result[i][j] = matrix1[i][j] + matrix2[i][j]
        }
        result
    }
}

fun multiplyMatrixToConstant(): Array<DoubleArray>? {
    print("Enter size of matrix: ")
    val row = scanner.nextInt()
    val column = scanner.nextInt()

    println("Enter matrix:")
    val matrix = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column) {
            matrix[i][j] = scanner.nextDouble()
        }
    }

    print("Enter constant: ")
    val constant = scanner.nextDouble()

    val result = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column)
            result[i][j] = matrix[i][j] * constant
    }
    return result
}

fun multiplyMatrices(): Array<DoubleArray>? {
    print("Enter size of first matrix: ")
    val row1 = scanner.nextInt()
    val column1 = scanner.nextInt()

    println("Enter first matrix:")
    val matrix1 = Array(row1) { DoubleArray(column1) }
    for (i in 0 until row1) {
        for (j in 0 until column1) {
            matrix1[i][j] = scanner.nextDouble()
        }
    }

    print("Enter size of second matrix: ")
    val row2 = scanner.nextInt()
    val column2 = scanner.nextInt()

    println("Enter second matrix:")
    val matrix2 = Array(row2) { DoubleArray(column2) }
    for (i in 0 until row2) {
        for (j in 0 until column2) {
            matrix2[i][j] = scanner.nextDouble()
        }
    }

    return if (column1 != row2)
        null
    else {
        val result = Array(row1) { DoubleArray(column2) }
        for (i in 0 until row1) {
            for (j in 0 until column2) {
                for (k in 0 until column1)
                    result[i][j] += matrix1[i][k] * matrix2[k][j]
            }
        }
        result
    }
}

fun transposeMatrix(): Array<DoubleArray>? {
    println("1. Main diagonal")
    println("2. Side diagonal")
    println("3. Vertical line")
    println("4. Horizontal line")
    print("Your choice: ")
    val option = scanner.nextInt()

    print("Enter size of matrix: ")
    val row = scanner.nextInt()
    val column = scanner.nextInt()

    println("Enter matrix:")
    val matrix = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column) {
            matrix[i][j] = scanner.nextDouble()
        }
    }

    return when (option) {
        1 -> transposeMainDiagonal(matrix)
        2 -> transposeSideDiagonal(matrix)
        3 -> transposeVerticalLine(matrix)
        4 -> transposeHorizontalLine(matrix)
        else -> null
    }
}

fun transposeMainDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val row = matrix.size
    val column = matrix.size

    val result = Array(row) { DoubleArray(column) }
    for (i in 0 until column) {
        for (j in 0 until row)
            result[i][j] = matrix[j][i]
    }
    return result
}

fun transposeSideDiagonal(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val row = matrix.size
    val column = matrix.size

    val result = Array(row) { DoubleArray(column) }
    for (i in 0 until column) {
        for (j in 0 until row)
            result[i][j] = matrix[column - 1 - j][row - 1 - i]
    }
    return result
}

fun transposeVerticalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val row = matrix.size
    val column = matrix.size

    val result = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column)
            result[i][j] = matrix[i][column - 1 - j]
    }
    return result
}

fun transposeHorizontalLine(matrix: Array<DoubleArray>): Array<DoubleArray> {
    val row = matrix.size
    val column = matrix.size

    val result = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column)
            result[i][j] = matrix[row - 1 - i][j]
    }
    return result
}

fun calculateDeterminant() {
    print("Enter size of matrix: ")
    val row = scanner.nextInt()
    val column = scanner.nextInt()

    println("Enter matrix:")
    val matrix = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column) {
            matrix[i][j] = scanner.nextDouble()
        }
    }

    println("The result is:")
    println(determine(matrix))
    println()
}

fun determine(matrix: Array<DoubleArray>): Double {
    if(matrix.size == 1)
        return matrix[0][0]

    if (matrix.size == 2)
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]

    val row = matrix.size
    val column = matrix[0].size
    val subMatrix = Array(row - 1) { DoubleArray(column - 1) }

    var total = 0.0
    for (k in 0 until column) {
        var r = 0
        for (i in 1 until row) {
            var c = 0
            for (j in 0 until column) {
                if (j == k)
                    continue
                subMatrix[r][c++] = matrix[i][j]
            }
            r++
        }
        total += (-1.0).pow(k.toDouble()) * determine(subMatrix) * matrix[0][k]
    }
    return total
}

fun cofactor(row: Int, column: Int, matrix: Array<DoubleArray>): Double {
    if(matrix.size == 1)
        return determine(matrix)

    val subMatrix = Array(matrix.size - 1) {DoubleArray(matrix.size - 1)}
    var r = 0
    for(i in matrix.indices) {
        if(i == row)
            continue
        var c = 0
        for(j in matrix.indices) {
            if(j == column)
                continue
            subMatrix[r][c++] = matrix[i][j]
        }
        r++
    }
    return (-1.0).pow(row + column) * determine(subMatrix)
}

fun inverseMatrix() {
    print("Enter size of matrix: ")
    val row = scanner.nextInt()
    val column = scanner.nextInt()

    println("Enter matrix:")
    val matrix = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column) {
            matrix[i][j] = scanner.nextDouble()
        }
    }

    val determinant = determine(matrix)

    println("The result is:")
    val result = Array(row) { DoubleArray(column) }
    for (i in 0 until row) {
        for (j in 0 until column) {
            result[i][j] = cofactor(i, j, matrix) / determinant
        }
    }

    println("The result is:")
    printMatrix(transposeMainDiagonal(result))
}

fun exit() {
    finish = true
}
