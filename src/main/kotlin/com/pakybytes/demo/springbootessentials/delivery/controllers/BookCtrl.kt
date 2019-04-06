package com.pakybytes.demo.springbootessentials.delivery.controllers

import com.pakybytes.demo.springbootessentials.core.services.BookService
import com.pakybytes.demo.springbootessentials.delivery.adapters.BookMapper
import com.pakybytes.demo.springbootessentials.delivery.adapters.ResponseAdapter
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.BookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.DeleteBookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.UpdateBookVM
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("book")
class BookCtrl(val bookMapper: BookMapper,
               val bookService: BookService,
               val responseAdapter: ResponseAdapter) {

    private val log = LoggerFactory.getLogger(BookCtrl::class.java)

    /** curl -X GET 'http://localhost:8080/book/1
     */
    @GetMapping("{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun get(@PathVariable(value = "id") id: Int): ResponseEntity<String> {

        val book = bookService.getById(id)

        log.info("Finish retrieving book: $book")
        return responseAdapter.transformResponse(book)
    }

    /** curl -d '{"title":"My Big Book", "year":2019}' -H "Content-Type: application/json" -X POST http://localhost:8080/book
     */
    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun post(@RequestBody(required = true) book: BookVM): ResponseEntity<String> {

        val model = bookMapper.toBook(book)

        val result = bookService.add(model)

        log.info("Finish adding new book, result: $result")
        return responseAdapter.transformResponse(result)
    }

    /** curl -d '{"id":1, "year":2020}' -H "Content-Type: application/json" -X PUT http://localhost:8080/book
     */
    @PutMapping("", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun put(@RequestBody(required = true) book: UpdateBookVM): ResponseEntity<String> {

        val model = bookMapper.toBook(book)

        val result = bookService.update(model)

        log.info("Finish adding new book with, result: $result")
        return responseAdapter.transformResponse(result)
    }

    /** curl -d '{"id":1}' -H "Content-Type: application/json" -X DELETE http://localhost:8080/book
     */
    @DeleteMapping("", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun delete(@RequestBody(required = true) book: DeleteBookVM): ResponseEntity<String> {

        val model = bookMapper.toBook(book)

        val result = bookService.delete(model)

        log.info("Finish deleting book with, result: $result")
        return responseAdapter.transformResponse(result)
    }
}