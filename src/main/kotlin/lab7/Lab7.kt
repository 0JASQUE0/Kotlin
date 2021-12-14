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
    fun serialization(shapeList: List<Shape>): String {
        return json.encodeToString(shapeList)
    }

    fun deserialization(string: String): List<Shape> {
        return json.decodeFromString(string)
    }
}

object FileIO {
    fun writeToFile(data: String, path: String) {
        FileWriter(path).buffered().use { writer ->
            writer.write(data)
        }
    }

    fun readFromFile(path: String): String {
        var text: String
        FileReader(path).buffered().use { reader ->
            text = reader.readText()
        }
        return text
    }
}