package Lab5

data class Book(val title: String, val author: Author, val genre: Genre, val year: Year)
data class Author(val name: String)
data class User(val firstName: String, val lastName: String)
data class Year(val year: Int)

enum class Genre {
    FANTASY,
    ADVENTURE,
    DETECTIVE,
    COMEDY,
    TRAGEDY,
    MYSTERY,
    HORROR
}

sealed class Status {
    object Available : Status()
    data class UsedBy(val user: User) : Status()
    object ComingSoon : Status()
    object Restoration : Status()
}

interface LibraryService {

    //search for books by one of the criteria
    fun findBooks(title: String? = null, author: Author? = null, year: Year? = null, genre: Genre? = null): List<Book>

    fun getAllBooks(): List<Book>
    fun getAllAvailableBooks(): List<Book>

    fun getBookStatus(book: Book): Status?
    fun getAllBookStatuses(): Map<Book, Status>

    fun setBookStatus(book: Book, status: Status)

    fun addBook(book: Book, status: Status = Status.Available)
    fun bookRestoration(book: Book, status: Status.Restoration)
    fun bookComingSoon(book: Book, status: Status.ComingSoon)

    fun registerUser(user: User)
    fun unregisterUser(user: User)

    // if the book was issued, it will be returned, otherwise it will return null
    fun takeBook(user: User, book: Book): Book?
    fun returnBook(book: Book)
}
