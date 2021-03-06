import java.util.*
import kotlin.math.*

enum class Type {
    DIGIT,
    BINARY_OPERATOR,
    FUNCTION,
    OPEN_BRACKET,
    CLOSE_BRACKET,
    CONSTANT,
    NULL,
    ERROR
}

class Operator constructor(var operator: String, val priority: Int, val type: Type)

fun calculateExpression(infixString: String): Double {
    val postfixList = infixToPostfix(infixString)
    val stack = Stack<String>()
    for (item in postfixList) {
        when (defineType(item)) {
            Type.DIGIT -> stack.push(item)
            Type.CONSTANT ->
                when (item) {
                    "pi" -> stack.push(Math.PI.toString())
                    "e" -> stack.push(Math.E.toString())
                }
            Type.FUNCTION ->
                when (item) {
                    "cos" -> stack.push(cos(stack.pop().toDouble()).toString())
                    "sin" -> stack.push(sin(stack.pop().toDouble()).toString())
                    "tg" -> stack.push(tan(stack.pop().toDouble()).toString())
                    "ctg" -> stack.push((1 / tan(stack.pop().toDouble())).toString())
                    "lg" -> stack.push(log10(stack.pop().toDouble()).toString())
                    "ln" -> stack.push(ln(stack.pop().toDouble()).toString())
                }
            Type.BINARY_OPERATOR -> stack.push(
                calculate(
                    item,
                    stack.pop().toDouble(),
                    stack.pop().toDouble()
                ).toString()
            )
            else -> throw IllegalArgumentException("incorrect input or something went wrong")
        }
    }
    return stack.pop().toDouble()
}

private fun infixToPostfix(infixString: String): List<String> {
    val list = stringToList(infixString)
    val postfixList = mutableListOf<String>()
    val stack = Stack<Operator>()

    for (item in list) {
        when (item.type) {
            Type.DIGIT -> postfixList.add(item.operator)
            Type.CONSTANT -> postfixList.add(item.operator)
            Type.BINARY_OPERATOR, Type.FUNCTION -> {
                while (stack.isNotEmpty() && item.priority <= stack.last().priority) {
                    postfixList.add(stack.pop().operator)
                }
                stack.push(item)
            }
            Type.OPEN_BRACKET -> stack.push(item)
            Type.CLOSE_BRACKET -> {
                if (stack.isEmpty()) throw IllegalArgumentException("incorrect input or something went wrong")
                var temp: String = stack.pop().operator
                while (temp != "(") {
                    if (stack.isEmpty()) throw IllegalArgumentException("incorrect input or something went wrong")
                    postfixList.add(temp)
                    temp = stack.pop().operator
                }
            }
            else -> throw IllegalArgumentException("incorrect input or something went wrong")
        }
    }
    while (stack.isNotEmpty()) {
        if (stack.last().operator == "(") throw IllegalArgumentException("incorrect input or something went wrong")
        postfixList.add(stack.pop().operator)
    }
    return postfixList
}

private fun stringToList(infixString: String): List<Operator> {
    val list = mutableListOf<Operator>()
    var lastElement = Operator("", -1, Type.NULL)
    val infixStringWithoutSpaces = infixString.replace(" ", "")
    var thereIsUnaryMinus = false
    var tempString = ""
    for (character in infixStringWithoutSpaces) {
        val isItUnaryMinusOrUnaryPlus: Boolean = (character == '-' || character == '+')
                && (lastElement.type == Type.OPEN_BRACKET || lastElement.type == Type.NULL || lastElement.type == Type.BINARY_OPERATOR) && tempString.isEmpty()
        val operatorIsStandingIncorrectly: Boolean = (character == '*' || character == '/' || character == '^')
                && (lastElement.type == Type.OPEN_BRACKET || lastElement.type == Type.NULL || lastElement.type == Type.BINARY_OPERATOR) && tempString.isEmpty()
        if (symbolIsNumberOrLetter(character)) {
            tempString += character
        } else if (isItUnaryMinusOrUnaryPlus) {
            if (character == '+') continue
            else {
                thereIsUnaryMinus = true
                continue
            }
        } else if (operatorIsStandingIncorrectly) {
            throw IllegalArgumentException("incorrect input or something went wrong")
        } else {
            if (tempString.isNotEmpty()) {
                if (defineType(tempString) == Type.ERROR)
                    throw IllegalArgumentException("incorrect input or something went wrong")
                lastElement = Operator(tempString, getPriority(tempString), defineType(tempString))
                tempString = ""
                if (thereIsUnaryMinus) {
                    lastElement.operator = "-" + lastElement.operator
                    thereIsUnaryMinus = false
                }
                list.add(lastElement)
            }
            lastElement =
                Operator(character.toString(), getPriority(character.toString()), defineType(character.toString()))
            list.add(lastElement)
        }
    }
    if (tempString.isNotEmpty()) {
        if (defineType(tempString) == Type.ERROR)
            throw IllegalArgumentException("incorrect input or something went wrong")
        lastElement = Operator(tempString, getPriority(tempString), defineType(tempString))
        if (thereIsUnaryMinus) {
            lastElement.operator = "-" + lastElement.operator
        }
        list.add(lastElement)
    }
    return list
}

private fun getPriority(operator: String): Int {
    return when (operator) {
        "cos", "sin", "tg", "ctg", "lg", "ln" -> 4
        "^" -> 3
        "*", "/" -> 2
        "+", "-" -> 1
        "(", ")" -> 0
        else -> -1
    }
}

private fun defineType(operator: String): Type {
    return when (operator) {
        "+", "-", "*", "/", "^" -> Type.BINARY_OPERATOR
        "cos", "sin", "tg", "ctg", "lg", "ln" -> Type.FUNCTION
        "(" -> Type.OPEN_BRACKET
        ")" -> Type.CLOSE_BRACKET
        "pi", "e" -> Type.CONSTANT
        else -> {
            return if (operator.toDoubleOrNull() != null) Type.DIGIT
            else Type.ERROR
        }
    }
}

private fun calculate(
    operator: String,
    secondOperand: Double,
    firstOperand: Double
): Double {
    when (operator) {
        "+" -> return firstOperand + secondOperand
        "-" -> return firstOperand - secondOperand
        "*" -> return firstOperand * secondOperand
        "/" -> return firstOperand / secondOperand
        "^" -> return firstOperand.pow(secondOperand)
    }
    return Double.NaN
}

private fun symbolIsNumberOrLetter(character: Char): Boolean {
    return defineType(character.toString()) == Type.DIGIT ||
            defineType(character.toString()) == Type.ERROR ||
            defineType(character.toString()) == Type.CONSTANT
}