package com.pakybytes.demo.springbootessentials.delivery.controllers

import com.pakybytes.demo.springbootessentials.core.services.BookService
import com.pakybytes.demo.springbootessentials.delivery.adapters.BookMapper
import com.pakybytes.demo.springbootessentials.delivery.adapters.ResponseAdapter
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.BookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.DeleteBookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.UpdateBookVM
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("book")
class BookCtrlRest(val bookMapper: BookMapper,
                   val bookService: BookService,
                   val responseAdapter: ResponseAdapter) : BookCtrl {

    private val log = LoggerFactory.getLogger(BookCtrlRest::class.java)

    override fun get(@PathVariable(value = "id") id: Int): ResponseEntity<String> {

        val book = bookService.getById(id)

        log.info("Finish retrieving book: $book")
        return responseAdapter.transformResponse(book)
    }

    override fun post(@RequestBody(required = true) book: BookVM): ResponseEntity<String> {

        val model = bookMapper.toBook(book)

        val result = bookService.add(model)

        log.info("Finish adding new book, result: $result")
        return responseAdapter.transformResponse(result)
    }


    override fun put(@RequestBody(required = true) book: UpdateBookVM): ResponseEntity<String> {

        val model = bookMapper.toBook(book)

        val result = bookService.update(model)

        log.info("Finish adding new book with, result: $result")
        return responseAdapter.transformResponse(result)
    }


    override fun delete(@RequestBody(required = true) book: DeleteBookVM): ResponseEntity<String> {

        val model = bookMapper.toBook(book)

        val result = bookService.delete(model)

        log.info("Finish deleting book with, result: $result")
        return responseAdapter.transformResponse(result)
    }
}