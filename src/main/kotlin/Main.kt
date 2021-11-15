fun main() {
    // first lab

    //val text = "В своём стремлении улучшить пользовательский опыт мы упускаем, что предприниматели в сети интернет, инициированные исключительно синтетически, превращены в посмешище, хотя само их существование приносит несомненную пользу обществу. Каждый из нас понимает очевидную вещь: разбавленное изрядной долей эмпатии, рациональное мышление однозначно фиксирует необходимость как самодостаточных, так и внешне зависимых концептуальных решений. Элементы политического процесса являются только методом политического участия и объединены в целые кластеры себе подобных. Лишь тщательные исследования конкурентов смешаны с не уникальными данными до степени совершенной неузнаваемости, из-за чего возрастает их статус бесполезности. С учётом сложившейся международной обстановки, начало повседневной работы по формированию позиции требует анализа существующих финансовых и административных условий.(рыба текст)"
    //val text = "a"
    val text = "1234"
    //val text = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
    println(alignText(text, 3, Alignment.LEFT))
    println("============================================\n")
    println(alignText(text, 100, Alignment.RIGHT))
    println("============================================\n")
    println(alignText(text, 100, Alignment.CENTER))
    println("============================================\n")
    println(alignText(text, 100, Alignment.JUSTIFY))

    // second lab

    //print(calculateExpression("cos(1) + sin(1) + tg(1) + ctg(1) + ln(e)  + lg(10)")) // 5.58
    //print(calculateExpression("3^2+-5*4 + (-2+3) * 2")) // -9
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
    println("min area is ${shapeMethods.minArea(shapeList)}")*/
}
