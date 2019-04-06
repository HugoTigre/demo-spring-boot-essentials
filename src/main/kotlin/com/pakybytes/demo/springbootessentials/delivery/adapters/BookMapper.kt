package com.pakybytes.demo.springbootessentials.delivery.adapters

import com.pakybytes.demo.springbootessentials.core.models.Book
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.BookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.DeleteBookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.UpdateBookVM
import org.springframework.stereotype.Component

@Component
class BookMapper {

    fun toBook(book: BookVM): Book =
            Book(
                    book.title,
                    book.year
            )

    fun toBook(book: UpdateBookVM): Book =
            Book(
                    "",
                    book.year,
                    book.id
            )

    fun toBook(book: DeleteBookVM): Book =
            Book(
                    "",
                    0,
                    book.id
            )

}