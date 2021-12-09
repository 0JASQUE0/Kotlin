package Lab5

class Library : LibraryService {
    private val users: MutableList<User> = mutableListOf()
    private val books: MutableMap<Book, Status> = mutableMapOf()

    override fun findBooks(title: String?, author: Author?, year: Year?, genre: Genre?): List<Book> {
        var listOfBooks: List<Book> = listOf()
        if (title != null) listOfBooks = findBooksByTitle(title)
        if (author != null) listOfBooks = findBooksByAuthor(author)
        if (year != null) listOfBooks = findBooksByYear(year)
        if (genre != null) listOfBooks = findBooksByGenre(genre)
        return listOfBooks
    }

    private fun findBooksByTitle(title: String): List<Book> {
        val listOfBooks: MutableList<Book> = mutableListOf()
        for (key in books.keys)
            if (key.title == title)
                listOfBooks.add(key)
        return listOfBooks
    }

    private fun findBooksByAuthor(author: Author): List<Book> {
        val listOfBooks: MutableList<Book> = mutableListOf()
        for (key in books.keys)
            if (key.author == author)
                listOfBooks.add(key)
        return listOfBooks
    }

    private fun findBooksByYear(year: Year): List<Book> {
        val listOfBooks: MutableList<Book> = mutableListOf()
        for (key in books.keys)
            if (key.year == year)
                listOfBooks.add(key)
        return listOfBooks
    }

    private fun findBooksByGenre(genre: Genre): List<Book> {
        val listOfBooks: MutableList<Book> = mutableListOf()
        for (key in books.keys)
            if (key.genre == genre)
                listOfBooks.add(key)
        return listOfBooks
    }

    override fun getAllBooks(): List<Book> {
        val listOfBooks: MutableList<Book> = mutableListOf()
        for (key in books.keys) listOfBooks.add(key)
        return listOfBooks
    }

    override fun getAllAvailableBooks(): List<Book> {
        val listOfBooks: MutableList<Book> = mutableListOf()
        for ((key, value) in books)
            if (value == Status.Available)
                listOfBooks.add(key)
        return listOfBooks
    }

    override fun getBookStatus(book: Book): Status? {
        return books[book]
    }

    override fun getAllBookStatuses(): Map<Book, Status> {
        return books.toMap()
    }

    override fun setBookStatus(book: Book, status: Status) {
        if (!books.containsKey(book))
            return
        books[book] = status
    }

    override fun addBook(book: Book, status: Status) {
        books[book] = status
    }

    override fun bookRestoration(book: Book, status: Status.Restoration) {
        books[book] = status
    }

    override fun bookComingSoon(book: Book, status: Status.ComingSoon) {
        books[book] = status
    }

    override fun registerUser(user: User) {
        if (users.contains(user))
            return
        users.add(user)
    }

    override fun unregisterUser(user: User) {
        if (!users.contains(user))
            return
        users.remove(user)
    }

    override fun takeBook(user: User, book: Book): Book? {
        if (!users.contains(user) || !books.containsKey(book))
            return null
        if (books[book] != Status.Available)
            return null
        if (amountOfTakenBooks(user) >= 3)
            return null
        setBookStatus(book, Status.UsedBy(user))
        return book
    }

    override fun returnBook(book: Book) {
        if (!books.containsKey(book))
            return
        setBookStatus(book, Status.Available)
    }

    private fun amountOfTakenBooks(user: User): Int {
        var result = 0
        for (value in books.values)
            if (value == Status.UsedBy(user))
                result++
        return result
    }
}

