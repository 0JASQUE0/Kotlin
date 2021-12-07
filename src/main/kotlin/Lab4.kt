class Matrix(matrix: Array<Array<Double>>) {

    private var data: Array<Array<Double>> = emptyArray()

    private val rows get() = data.size

    private val columns get() = data[0].size

    init {
        if (matrix.isEmpty() || matrix[0].isEmpty())
            throw IllegalArgumentException("Matrix is empty")
        val size = matrix[0].size
        for (i in matrix.indices)
            if (matrix[i].size != size)
                throw IllegalArgumentException("There is no such matrix")
        data = Array(matrix.size) { Array(matrix[0].size) { 0.0 } }
        for (i in matrix.indices)
            for (j in matrix[0].indices)
                data[i][j] = matrix[i][j]
    }

    operator fun plus(other: Matrix): Matrix {
        if (this.rows != other.rows || this.columns != other.columns)
            throw IllegalArgumentException("Different dimensions")
        val resultMatrix: Array<Array<Double>> = Array(this.rows) { Array(this.columns) { 0.0 } }
        for (i in data.indices)
            for (j in data[0].indices)
                resultMatrix[i][j] = this[i, j] + other[i, j]
        return Matrix(resultMatrix)
    }

    operator fun plusAssign(other: Matrix) {
        if (this.rows != other.rows || this.columns != other.columns)
            throw IllegalArgumentException("Different dimensions")
        for (i in data.indices)
            for (j in data[0].indices)
                this[i, j] = this[i, j] + other[i, j]
    }

    operator fun minus(other: Matrix): Matrix {
        if (this.rows != other.rows || this.columns != other.columns)
            throw IllegalArgumentException("Different dimensions")
        val resultMatrix: Array<Array<Double>> = Array(this.rows) { Array(this.columns) { 0.0 } }
        for (i in data.indices)
            for (j in data[0].indices)
                resultMatrix[i][j] = this[i, j] - other[i, j]
        return Matrix(resultMatrix)
    }

    operator fun minusAssign(other: Matrix) {
        if (this.rows != other.rows || this.columns != other.columns)
            throw IllegalArgumentException("Different dimensions")
        for (i in data.indices)
            for (j in data[0].indices)
                this[i, j] = this[i, j] - other[i, j]
    }

    operator fun times(other: Matrix) : Matrix {
        if (this.columns != other.rows)
            throw IllegalArgumentException("These matrices cannot be multiplied")
        val resultMatrix: Array<Array<Double>> = Array(this.rows) { Array(other.columns) { 0.0 } }
        for (i in data.indices)
            for (j in resultMatrix[0].indices)
                for (k in data[0].indices)
                    resultMatrix[i][j] += this[i, k] * other[k, j]
        return Matrix(resultMatrix)
    }

    operator fun timesAssign(other: Matrix) {
        if (this.columns != other.rows)
            throw IllegalArgumentException("These matrices cannot be multiplied")
        val resultMatrix: Array<Array<Double>> = Array(this.rows) { Array(other.columns) { 0.0 } }
        for (i in data.indices)
            for (j in resultMatrix[0].indices)
                for (k in data[0].indices)
                    resultMatrix[i][j] += this[i, k] * other[k, j]
        data = resultMatrix
    }

    operator fun times(scalar: Double) : Matrix {
        val resultMatrix: Array<Array<Double>> = Array(this.rows) { Array(this.columns) { 0.0 } }
        for (i in data.indices)
            for (j in data[0].indices)
                resultMatrix[i][j] = this[i, j] * scalar
        return Matrix(resultMatrix)
    }

    operator fun timesAssign(scalar: Double) {
        for (i in data.indices)
            for (j in data[0].indices)
                this[i, j] *= scalar
    }

    operator fun div(scalar: Double) : Matrix {
        val resultMatrix: Array<Array<Double>> = Array(this.rows) { Array(this.columns) { 0.0 } }
        for (i in data.indices)
            for (j in data[0].indices)
                resultMatrix[i][j] = this[i, j] / scalar
        return Matrix(resultMatrix)
    }

    operator fun divAssign(scalar: Double) {
        for (i in data.indices)
            for (j in data[0].indices)
                this[i, j] /= scalar
    }

    operator fun set(i: Int, j: Int, value: Double) {
        data[i][j] = value
    }

    operator fun get(i: Int, j: Int): Double {
        return data[i][j]
    }

    operator fun unaryMinus(): Matrix {
        val resultMatrix: Array<Array<Double>> = Array(this.rows) { Array(this.columns) { 0.0 } }
        for (i in data.indices)
            for (j in data[0].indices)
                resultMatrix[i][j] = -this[i, j]
        return Matrix(resultMatrix)
    }

    operator fun unaryPlus(): Matrix {
        return this
    }

    override fun toString(): String {
        var string = ""
        for (i in data.indices) {
            for (j in data[0].indices)
                string += this[i, j].toString() + " "
            string += "\n"
        }
        return string
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix

        if (!data.contentDeepEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentDeepHashCode()
    }

}
