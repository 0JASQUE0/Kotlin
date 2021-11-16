enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}

fun alignText(
    text: String,
    lineWidth: Int = 120,
    alignment: Alignment = Alignment.LEFT
): String {
    return when (alignment) {
        Alignment.LEFT -> alignTextLeft(text, lineWidth)
        Alignment.RIGHT -> alignTextRight(text, lineWidth)
        Alignment.CENTER -> alignTextCenter(text, lineWidth)
        Alignment.JUSTIFY -> alignTextJustify(text, lineWidth)
    }
}

private fun alignTextLeft(
    text: String,
    lineWidth: Int = 120,
): String {
    checkingLineWidth(lineWidth)
    var result = ""
    for (line in text.lines()) {
        var currentLine = line.trim()
        while (currentLine.contains("  "))
            currentLine = currentLine.replace("  ", " ")
        while (currentLine.length > lineWidth) {
            val indexOfLastSpace: Int = currentLine.substring(0, lineWidth).lastIndexOf(' ')
            if (indexOfLastSpace != -1) {
                result += currentLine.substring(0, indexOfLastSpace + 1).trim() + '\n'
                currentLine = currentLine.drop(indexOfLastSpace + 1)
            } else {
                result += currentLine.substring(0, lineWidth).trim() + '\n'
                currentLine = currentLine.drop(lineWidth)
            }
        }
        result += currentLine + '\n'
    }
    return result
}

private fun alignTextRight(
    text: String,
    lineWidth: Int = 120,
): String {
    checkingLineWidth(lineWidth)
    var result = ""
    for (line in text.lines()) {
        var currentLine = line.trim()
        while (currentLine.contains("  "))
            currentLine = currentLine.replace("  ", " ")
        while (currentLine.length > lineWidth) {
            val indexOfLastSpace: Int = currentLine.substring(0, lineWidth).lastIndexOf(' ')
            if (indexOfLastSpace != -1) {
                result += currentLine.substring(0, indexOfLastSpace + 1).trim().padStart(lineWidth, ' ') + '\n'
                currentLine = currentLine.drop(indexOfLastSpace + 1)
            } else {
                result += currentLine.substring(0, lineWidth).trim().padStart(lineWidth, ' ') + '\n'
                currentLine = currentLine.drop(lineWidth)
            }
        }
        result += currentLine.padStart(lineWidth, ' ') + '\n'
    }
    return result
}

private fun alignTextCenter(
    text: String,
    lineWidth: Int = 120,
): String {
    checkingLineWidth(lineWidth)
    var result = ""
    for (line in text.lines()) {
        var currentLine = line.trim()
        while (currentLine.contains("  "))
            currentLine = currentLine.replace("  ", " ")
        while (currentLine.length > lineWidth) {
            val indexOfLastSpace: Int = currentLine.substring(0, lineWidth).lastIndexOf(' ')
            if (indexOfLastSpace != -1) {
                result += currentLine.substring(0, indexOfLastSpace + 1).trim().padStart(lineWidth, ' ')
                    .drop((lineWidth - indexOfLastSpace) / 2).padEnd(lineWidth, ' ') + '\n'
                currentLine = currentLine.drop(indexOfLastSpace + 1)
            } else {
                result += currentLine.substring(0, lineWidth).trim().padStart(lineWidth, ' ') + '\n'
                currentLine = currentLine.drop(lineWidth)
            }
        }
        val lengthOfLine: Int = currentLine.length
        result += currentLine.padStart(lineWidth, ' ').drop((lineWidth - lengthOfLine) / 2)
            .padEnd(lineWidth, ' ') + '\n'
    }
    return result
}

private fun alignTextJustify(
    text: String,
    lineWidth: Int = 120,
): String {
    checkingLineWidth(lineWidth)
    var result = ""
    for (line in text.lines()) {
        var currentLine = line.trim()
        while (currentLine.contains("  "))
            currentLine = currentLine.replace("  ", " ")
        while (currentLine.length > lineWidth) {
            val indexOfLastSpace: Int = currentLine.substring(0, lineWidth).lastIndexOf(' ')
            if (indexOfLastSpace != -1) {
                result += makingSpaces(currentLine.substring(0, indexOfLastSpace + 1).trim(), lineWidth) + '\n'
                //result += currentLine.substring(0, indexOfLastSpace + 1).trim() + '\n'
                currentLine = currentLine.drop(indexOfLastSpace + 1)
            } else {
                result += currentLine.substring(0, lineWidth).trim() + '\n'
                currentLine = currentLine.drop(lineWidth)
            }
        }
        result += currentLine + '\n'
    }
    return result
}

//this function makes the spaces wider (for alignTextJustify)
private fun makingSpaces(
    line: String,
    lineWidth: Int
): String {
    var amountOfSpaces: Int = line.length
    for (word in line.split(' '))
        amountOfSpaces -= word.length
    if (amountOfSpaces == 0) return line
    val widthOfSpaces: Int = (lineWidth - line.length) / amountOfSpaces + 1
    var amountOfBigSpaces: Int = (lineWidth - line.length) % amountOfSpaces
    var tempString = ""
    for (word in line.split(' ')) {
        var iterator = widthOfSpaces
        if (amountOfBigSpaces > 0) {
            iterator++
            amountOfBigSpaces--
        }
        tempString += word
        repeat(iterator) {
            tempString += " "
        }
    }
    return tempString
}

private fun checkingLineWidth(lineWidth: Int) {
    if (lineWidth < 1)
        throw IllegalArgumentException("Line width should be greater than 0")
}