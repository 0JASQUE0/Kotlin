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

class Operator constructor(var operator: String, val priority: Int, val type:Type)

fun calculateExpression(infixString: String): Double {
    val postfixList = infixToPostfix(infixString)
    val stack = Stack<String>()
    for (item in postfixList) {
        if (defineType(item) == Type.DIGIT) {
            stack.push(item)
        } else if (defineType(item) == Type.CONSTANT){
            when (item) {
                "pi" -> stack.push(Math.PI.toString())
                "e" -> stack.push(Math.E.toString())
            }
        } else if (defineType(item) == Type.FUNCTION) {
            when (item) {
                "cos" -> stack.push(cos(stack.pop().toDouble()).toString())
                "sin" -> stack.push(sin(stack.pop().toDouble()).toString())
                "tg" -> stack.push(tan(stack.pop().toDouble()).toString())
                "ctg" -> stack.push((1 / tan(stack.pop().toDouble())).toString())
                "lg" -> stack.push(log10(stack.pop().toDouble()).toString())
                "ln" -> stack.push(ln(stack.pop().toDouble()).toString())
            }
        } else if (defineType(item) == Type.BINARY_OPERATOR)
            stack.push(calculate(item, stack.pop().toDouble(), stack.pop().toDouble()).toString())
    }
    return stack.pop().toDouble()
}

fun infixToPostfix(infixString: String): List<String> {
    val list =  stringToList(infixString)
    val postfixList = mutableListOf<String>()
    val stack = Stack<Operator>()

    for (item in list) {
        when (item.type) {
            Type.DIGIT -> postfixList.add(item.operator)
            Type.CONSTANT -> postfixList.add(item.operator)
            Type.BINARY_OPERATOR, Type.FUNCTION -> {
                while(stack.isNotEmpty() && item.priority <= stack.last().priority) {
                    postfixList.add(stack.pop().operator)
                }
                stack.push(item)
            }
            Type.OPEN_BRACKET -> stack.push(item)
            Type.CLOSE_BRACKET -> {
                if (stack.isEmpty()) throw Exception("incorrect input or something went wrong")
                var temp: String = stack.pop().operator
                while (temp != "(") {
                    if (stack.isEmpty()) throw Exception("incorrect input or something went wrong")
                    postfixList.add(temp)
                    temp = stack.pop().operator
                }
            }
            else -> throw Exception("incorrect input or something went wrong")
        }
    }
    while (stack.isNotEmpty()) {
        if (stack.last().operator == "(") throw Exception("incorrect input or something went wrong")
        postfixList.add(stack.pop().operator)
    }
    return postfixList
}

fun stringToList (infixString: String): List<Operator> {
    val list = mutableListOf<Operator>()
    var lastElement = Operator("", -1, Type.NULL)
    val infixStringWithoutSpaces = infixString.replace(" ", "")
    var flag = false
    var tempString = ""
    for (character in infixStringWithoutSpaces) {
        if (defineType(character.toString()) == Type.DIGIT || defineType(character.toString()) == Type.ERROR || defineType(character.toString()) == Type.CONSTANT) {
            tempString += character
        } else if ((character == '-' || character == '+')
            && (lastElement.type == Type.OPEN_BRACKET || lastElement.type == Type.NULL || lastElement.type == Type.BINARY_OPERATOR) && tempString.isEmpty()) {
            // unary '+' and '-'
            if (character == '+') continue
            else {
                flag = true
                continue
            }
        } else if ((character == '*' || character == '/' || character == '^')
            && (lastElement.type == Type.OPEN_BRACKET || lastElement.type == Type.NULL || lastElement.type == Type.BINARY_OPERATOR) && tempString.isEmpty()) {
            throw Exception("incorrect input or something went wrong")
        } else {
            if (tempString.isNotEmpty()) {
                if (defineType(tempString) == Type.DIGIT)
                    if (tempString.toDoubleOrNull() == null)
                        throw Exception("incorrect input or something went wrong")
                lastElement = Operator(tempString, getPriority(tempString), defineType(tempString))
                tempString = ""
                if (flag){
                    lastElement.operator = "-" + lastElement.operator
                    flag = false
                }
                list.add(lastElement)
            }
            lastElement = Operator(character.toString(), getPriority(character.toString()), defineType(character.toString()))
            list.add(lastElement)
        }
    }
    if (tempString.isNotEmpty()) {
        if (defineType(tempString) == Type.DIGIT)
            if (tempString.toDoubleOrNull() == null)
                throw Exception("incorrect input or something went wrong")
        lastElement = Operator(tempString, getPriority(tempString), defineType(tempString))
        if (flag){
            lastElement.operator = "-" + lastElement.operator
        }
        list.add(lastElement)
    }
    return list
}

fun getPriority(operator: String): Int {
    return when (operator) {
        "cos", "sin", "tg", "ctg", "lg", "ln" -> 4
        "^" -> 3
        "*", "/" -> 2
        "+", "-" -> 1
        "(", ")" -> 0
        else -> -1
    }
}

fun defineType(operator: String): Type {
    return when (operator) {
        "+", "-", "*", "/", "^" -> Type.BINARY_OPERATOR
        "cos", "sin", "tg", "ctg", "lg", "ln" -> Type.FUNCTION
        "(" -> Type.OPEN_BRACKET
        ")" -> Type.CLOSE_BRACKET
        "pi", "e" -> Type.CONSTANT
        else -> {
            return if(operator.toDoubleOrNull() != null) Type.DIGIT
            else Type.ERROR
        }
    }
}

fun calculate(
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