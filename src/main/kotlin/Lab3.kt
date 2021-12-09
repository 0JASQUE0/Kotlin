import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.abs
import kotlinx.serialization.Serializable

interface Shape {
    fun calcArea(): Double
    fun calcPerimeter(): Double
}

@Serializable
class Circle(val radius: Double) : Shape {
    init {
        if (radius <= 0) throw IllegalArgumentException("radius of the circle cannot be <= 0")
    }

    override fun calcArea(): Double {
        return Math.PI * radius.pow(2)
    }

    override fun calcPerimeter(): Double {
        return 2 * Math.PI * radius
    }
}

@Serializable
class Square(val a: Double) : Shape {
    init {
        if (a <= 0) throw  IllegalArgumentException("length of the side of the square cannot be <= 0")
    }

    override fun calcArea(): Double {
        return a.pow(2)
    }

    override fun calcPerimeter(): Double {
        return a * 4
    }
}

@Serializable
class Rectangle(val a: Double, val b: Double) : Shape {
    init {
        if (a <= 0 || b <= 0) throw IllegalArgumentException("length of the side of the rectangle cannot be <= 0")
    }

    override fun calcArea(): Double {
        return a * b
    }

    override fun calcPerimeter(): Double {
        return (a + b) * 2
    }
}

@Serializable
class Triangle(val a: Double, val b: Double, val c: Double) : Shape {
    init {
        if (a <= 0 || b <= 0 || c <= 0) throw IllegalArgumentException("length of the side of the triangle cannot be <= 0")
        if (a + b <= c || a + c <= b || b + c <= a) throw IllegalArgumentException("triangle with sides $a, $b, $c dose not exist")
    }

    override fun calcArea(): Double {
        val semiPerimeter = calcPerimeter() / 2
        return sqrt(semiPerimeter * (semiPerimeter - a) * (semiPerimeter - b) * (semiPerimeter - c))
    }

    override fun calcPerimeter(): Double {
        return a + b + c
    }
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createSquare(a: Double): Square
    fun createRectangle(a: Double, b: Double): Rectangle
    fun createTriangle(a: Double, b: Double, c: Double): Triangle

    fun createRandomCircle(): Circle
    fun createRandomSquare(): Square
    fun createRandomRectangle(): Rectangle
    fun createRandomTriangle(): Triangle

    fun createRandomShape(): Shape
}

class ShapeFactoryImpl : ShapeFactory {
    override fun createCircle(radius: Double): Circle {
        return Circle(radius)
    }

    override fun createSquare(a: Double): Square {
        return Square(a)
    }

    override fun createRectangle(a: Double, b: Double): Rectangle {
        return Rectangle(a, b)
    }

    override fun createTriangle(a: Double, b: Double, c: Double): Triangle {
        return Triangle(a, b, c)
    }

    override fun createRandomCircle(): Circle {
        return Circle(randomDouble())
    }

    override fun createRandomSquare(): Square {
        return Square(randomDouble())
    }

    override fun createRandomRectangle(): Rectangle {
        return Rectangle(randomDouble(), randomDouble())
    }

    override fun createRandomTriangle(): Triangle {
        val a = randomDouble()
        val b = randomDouble()
        val c = ((abs(a - b).toInt() + 1) until (a + b).toInt()).random().toDouble()
        return Triangle(a, b, c)
    }

    override fun createRandomShape(): Shape {
        return when ((0..3).random()) {
            0 -> createRandomCircle()
            1 -> createRandomSquare()
            2 -> createRandomRectangle()
            3 -> createRandomTriangle()
            else -> throw Exception("something went wrong")
        }
    }

    private fun randomDouble(): Double {
        return (1..100).random().toDouble()
    }
}

class ShapeMethods {
    fun sumOfPerimeters(shapes: List<Shape>): Double {
        var sum = 0.0
        for (shape in shapes)
            sum += shape.calcPerimeter()
        return sum
    }

    fun sumOfAreas(shapes: List<Shape>): Double {
        var sum = 0.0
        for (shape in shapes)
            sum += shape.calcArea()
        return sum
    }

    fun maxPerimeter(shapes: List<Shape>): Shape? {
        if (shapes.isEmpty()) return null
        var maxPerimeter = 0.0
        var maxShape = shapes[0]
        for (shape in shapes)
            if (shape.calcPerimeter() > maxPerimeter) {
                maxPerimeter = shape.calcPerimeter()
                maxShape = shape
            }
        return maxShape
    }

    fun minPerimeter(shapes: List<Shape>): Shape? {
        if (shapes.isEmpty()) return null
        var minPerimeter = Double.MAX_VALUE
        var minShape = shapes[0]
        for (shape in shapes)
            if (shape.calcPerimeter() < minPerimeter) {
                minPerimeter = shape.calcPerimeter()
                minShape = shape
            }
        return minShape
    }

    fun maxArea(shapes: List<Shape>): Shape? {
        if (shapes.isEmpty()) return null
        var maxArea = 0.0
        var maxShape = shapes[0]
        for (shape in shapes)
            if (shape.calcArea() > maxArea) {
                maxArea = shape.calcArea()
                maxShape = shape
            }
        return maxShape
    }

    fun minArea(shapes: List<Shape>): Shape? {
        if (shapes.isEmpty()) return null
        var minArea = Double.MAX_VALUE
        var minShape = shapes[0]
        for (shape in shapes)
            if (shape.calcArea() < minArea) {
                minArea = shape.calcArea()
                minShape = shape
            }
        return minShape
    }
}

