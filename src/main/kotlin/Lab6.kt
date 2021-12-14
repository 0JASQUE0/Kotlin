object ShapeComparator {
    val perimeterComparatorAsc = compareBy<Shape> { it.calcPerimeter() }
    val perimeterComparatorDesc = compareByDescending<Shape> { it.calcPerimeter() }
    val areaComparatorAsc = compareBy<Shape> { it.calcArea() }
    val areaComparatorDesc = compareByDescending<Shape> { it.calcArea() }
    val radiusComparatorAsc = compareBy<Circle> { it.radius }
    val radiusComparatorDesc = compareByDescending<Circle> { it.radius }
}

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(new: T) {
        allShapes.add(new)
    }

    fun addAll(new: Collection<T>) {
        allShapes.addAll(new)
    }

    fun getAll(): List<T> {
        return allShapes
    }

    fun getAllSorted(comparator: Comparator<in T>): List<T> {
        return allShapes.sortedWith(comparator)
    }

    fun getAllByClass(shapeClass: Class<out T>): List<T> {
        val listOfShapes = mutableListOf<T>()
        for (shape in allShapes)
            if (shape.javaClass == shapeClass)
                listOfShapes.add(shape)
        return listOfShapes
    }
}
