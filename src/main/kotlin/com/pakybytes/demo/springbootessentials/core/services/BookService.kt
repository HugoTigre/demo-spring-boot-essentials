package com.pakybytes.demo.springbootessentials.core.services


import com.pakybytes.demo.springbootessentials.core.data.BookRepo
import com.pakybytes.demo.springbootessentials.core.models.Book
import com.pakybytes.demo.springbootessentials.core.models.status.MsgStatus
import com.pakybytes.demo.springbootessentials.core.models.status.StatusResult
import com.pakybytes.demo.springbootessentials.core.services.status.StatusService
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BookService(val statusServ: StatusService,
                  val bookRepo: BookRepo) {

    private val log = LoggerFactory.getLogger(BookService::class.java)

    private val queryTimeout = 5000L


    /** Create a new Wine (product and wine) in a single transaction
     */
    fun add(book: Book): StatusResult {

        log.info("Adding new book $book")

        val bookId = bookRepo.insert(book)

        if (bookId == null) {
            log.error("Error inserting new book: $book")
            return statusServ.build(MsgStatus.DAL_ERROR, "Error creating new Book")
        }

        log.info("New book inserted: $book")
        return statusServ.build(MsgStatus.OK, resourceId = bookId.toString())
    }

    /** Create a new Wine (product and wine) in a single transaction
     */
    fun update(book: Book): StatusResult {

        log.info("Updating book $book")

        if (bookRepo.update(book) > 0) {
            log.info("Book inserted: $book")
        }

        return statusServ.build(MsgStatus.OK, MsgStatus.getDescr(MsgStatus.OK), resourceId = book.id.toString())
    }

    /** Create a new Wine (product and wine) in a single transaction
     */
    fun delete(book: Book): StatusResult {

        log.info("Deleting book $book")

        if (bookRepo.delete(book) > 0) {
            log.info("Book delete: $book")
        }

        return statusServ.build(MsgStatus.OK, MsgStatus.getDescr(MsgStatus.OK), resourceId = book.id.toString())
    }

    /** ### Gets a [Book] by id.
     */
    fun getById(id: Int): Book? {
        return runBlocking {
            withTimeoutOrNull(queryTimeout) {
                bookRepo.queryById(id)
            }
        }
    }
}