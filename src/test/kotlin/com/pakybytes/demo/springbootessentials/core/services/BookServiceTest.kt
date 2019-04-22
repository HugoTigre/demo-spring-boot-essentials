package com.pakybytes.demo.springbootessentials.core.services

import com.pakybytes.demo.springbootessentials.core.data.BookRepo
import com.pakybytes.demo.springbootessentials.core.models.status.MsgStatus
import com.pakybytes.demo.springbootessentials.core.services.status.StatusService
import common.Mocks
import common.UnitTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`

@UnitTest
@DisplayName("Book Service Unit Tests")
internal class BookServiceTest {

    @Mock
    lateinit var statusServ: StatusService

    @Mock
    lateinit var bookRepo: BookRepo

    @InjectMocks
    lateinit var bookService: BookService

    @Test
    fun `add book should return Ok`() {
        val bookId = 1
        `when`(bookRepo.insert(Mocks.book)).thenReturn(bookId)
        val statusOkInsertBook = statusServ.build(MsgStatus.OK, resourceId = bookId.toString())
        val result = bookService.add(Mocks.book)
        assertEquals(statusOkInsertBook, result)
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun getById() {
    }
}
