package lab7

import Circle
import Rectangle
import Shape
import Square
import Triangle
import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.*
import java.io.FileReader
import java.io.FileWriter

private val json = Json {
    prettyPrint = true

    serializersModule = SerializersModule {
        polymorphic(Shape::class) {
            subclass(Circle::class)
            subclass(Square::class)
            subclass(Rectangle::class)
            subclass(Triangle::class)
        }
    }
}


object Serialization {
    fun serialization(shapeList: MutableList<Shape>): String {
        return json.encodeToString(shapeList)
    }

    fun deserialization(string: String): MutableList<Shape> {
        return json.decodeFromString(string)
    }
}

object FileIO {
    private const val inputPath = "D:\\Kotlin\\src\\main\\kotlin\\lab7\\in.lab7.json"
    private const val outputPath = "D:\\Kotlin\\src\\main\\kotlin\\lab7\\out.lab7.json"

    fun writeToFile(data: String, path: String = outputPath) {
        FileWriter(path).buffered().use { writer ->
            writer.write(data)
        }
    }

    fun readFromFile(): String {
        var text: String
        FileReader(inputPath).buffered().use { reader ->
            text = reader.readText()
        }
        return text
    }
}