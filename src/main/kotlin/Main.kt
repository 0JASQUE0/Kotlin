import Lab5.*

fun main() {
    // first lab

    /*val text = "В своём стремлении улучшить пользовательский опыт мы упускаем, что предприниматели в сети интернет, инициированные исключительно синтетически, превращены в посмешище, хотя само их существование приносит несомненную пользу обществу. Каждый из нас понимает очевидную вещь: разбавленное изрядной долей эмпатии, рациональное мышление однозначно фиксирует необходимость как самодостаточных, так и внешне зависимых концептуальных решений. Элементы политического процесса являются только методом политического участия и объединены в целые кластеры себе подобных. Лишь тщательные исследования конкурентов смешаны с не уникальными данными до степени совершенной неузнаваемости, из-за чего возрастает их статус бесполезности. С учётом сложившейся международной обстановки, начало повседневной работы по формированию позиции требует анализа существующих финансовых и административных условий.(рыба текст)"
    //val text = "a"
    //val text = "1234"
    //val text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    println(alignText(text, -100, Alignment.LEFT))
    println("============================================\n")
    println(alignText(text, 100, Alignment.RIGHT))
    println("============================================\n")
    println(alignText(text, 100, Alignment.CENTER))
    println("============================================\n")
    println(alignText(text, 100, Alignment.JUSTIFY))*/

    // second lab

    //println(calculateExpression("cos(1) + sin(1) + tg(1) + ctg(1) + ln(e)  + lg(10)")) // 5.58
    //println(calculateExpression("3^2+-5*4 + (-2+3) * 2")) // -9
    //print(calculateExpression("4asd5")) // exception
    //print(calculateExpression("*3+2")) // exception
    //print(calculateExpression("3+*2")) // exception
    //print(calculateExpression("(5+3")) // exception
    //print(calculateExpression("(5+3))")) // exception

    //third lab

    /*val shapeFactory = ShapeFactoryImpl()
    val shapeMethods = ShapeMethods()
    val shapeList: MutableList<Shape> = arrayListOf()
    shapeList.add(shapeFactory.createCircle(4.0))
    shapeList.add(shapeFactory.createSquare(5.0))
    shapeList.add(shapeFactory.createRectangle(2.0, 3.0))
    shapeList.add(shapeFactory.createTriangle(3.0, 4.0, 5.0))

    shapeList.add(shapeFactory.createRandomCircle())
    shapeList.add(shapeFactory.createRandomSquare())
    shapeList.add(shapeFactory.createRandomRectangle())
    shapeList.add(shapeFactory.createRandomTriangle())

    shapeList.add(shapeFactory.createRandomShape())

    println("sum of perimeters is ${shapeMethods.sumOfPerimeters(shapeList)}")
    println("sum of areas is ${shapeMethods.sumOfAreas(shapeList)}")
    println("max perimeter is ${shapeMethods.maxPerimeter(shapeList)}")
    println("min perimeter is ${shapeMethods.minPerimeter(shapeList)}")
    println("max area is ${shapeMethods.maxArea(shapeList)}")
    println("min area is ${shapeMethods.minArea(shapeList)}")

    val circle: Circle = shapeFactory.createCircle(5.0)
    print(circle.radius)*/

    //fourth lab

    /*val array1: Array<Array<Double>> = arrayOf(
        arrayOf(1.0, 2.0),
        arrayOf(3.0, 4.0)
    )

    val array2: Array<Array<Double>> = arrayOf(
        arrayOf(1.0),
        arrayOf(3.0)
    )

    val matrix1 = Matrix(array1)

    println(matrix1.toString())
    matrix1[0, 1] = 3.0
    println(matrix1.toString())
    matrix1[0, 1] = 2.0

    println((matrix1 + Matrix(array1)).toString())
    println((matrix1 - Matrix(array1)).toString())
    matrix1 += Matrix(array1)
    println(matrix1.toString())
    matrix1 -= Matrix(array1)
    println(matrix1.toString())
    println((-matrix1).toString())
    println((matrix1 * 10.0).toString())
    matrix1 *= 10.0
    println(matrix1.toString())
    println((matrix1 / 10.0).toString())
    matrix1 /= 10.0
    println(matrix1.toString())
    println((matrix1 * Matrix(array1)).toString())
    println((matrix1 * Matrix(array2)).toString())

    matrix1 *= Matrix(array1)
    println(matrix1.toString())

    matrix1 *= Matrix(array2)
    println(matrix1.toString())

    val matrix2 = Matrix(array1)

    println(matrix2 == Matrix(array1))
    println(matrix2 == Matrix(array2))*/

    //fifth lab

    val library: LibraryService = Library()

    val book1 = Book("Автостопом по галактике", Author("Дуглас Адамс"), Genre.FANTASY, Year(1979))

    library.addBook(Book("Властелин Колец", Author("Джон Р. Р. Толкин"), Genre.ADVENTURE, Year(1979)), Status.Available)
    library.addBook(Book("Гордость и предубеждение", Author("Джейн Остин"), Genre.TRAGEDY, Year(1813)), Status.Available)
    library.addBook(Book("Шерлок Холмс", Author("Конан Дойль"), Genre.DETECTIVE, Year(1892)), Status.Available)
    library.addBook(Book("Мастер и Маргарита", Author("\n" + "Мастер и Маргарита\n" + "Михаил Булгаков"), Genre.MYSTERY, Year(1967)), Status.Available)
    library.addBook(Book("Оно", Author("Стивен Кинг"), Genre.HORROR, Year(1986)), Status.Available)
    library.addBook(book1, Status.Available)
    library.addBook(Book("Ревизор", Author("Николай Гоголь"), Genre.COMEDY, Year(1836)), Status.Available)

    val user1 = User("Дмитрий", "Квитко")
    val user2 = User("Олег", "Баязитов")
    val user3 = User("Иван", "Бабкин")

    library.registerUser(user1)
    library.registerUser(user2)
    library.registerUser(user3)

    library.takeBook(user1, book1)
    library.returnBook(book1)

    library.unregisterUser(user1)

    var result: List<Book> = library.findBooks(genre = Genre.HORROR)
    for (i in result)
        println(i)

    result = library.findBooks(year = Year(1979))
    for (i in result)
        println(i)
}