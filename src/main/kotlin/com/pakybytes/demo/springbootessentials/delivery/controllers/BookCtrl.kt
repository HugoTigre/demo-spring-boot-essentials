package com.pakybytes.demo.springbootessentials.delivery.controllers

import com.pakybytes.demo.springbootessentials.delivery.viewmodels.BookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.DeleteBookVM
import com.pakybytes.demo.springbootessentials.delivery.viewmodels.UpdateBookVM
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

interface BookCtrl {

    /** curl -X GET 'http://localhost:8080/book/1
     */
    @ApiOperation("Returns a Book by ID.", notes = "", response = String::class)
    @ApiResponses(value = [
        ApiResponse(code = 200, message = "Book Information")
    ])
    @GetMapping("{id}", produces = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun get(@PathVariable(value = "id") id: Int): ResponseEntity<String>


    /** curl -d '{"title":"My Big Book", "year":2019}' -H "Content-Type: application/json" -X POST http://localhost:8080/book
     */
    @PostMapping(consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun post(@RequestBody(required = true) book: BookVM): ResponseEntity<String>


    /** curl -d '{"id":1, "year":2020}' -H "Content-Type: application/json" -X PUT http://localhost:8080/book
     */
    @PutMapping("", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun put(@RequestBody(required = true) book: UpdateBookVM): ResponseEntity<String>


    /** curl -d '{"id":1}' -H "Content-Type: application/json" -X DELETE http://localhost:8080/book
     */
    @DeleteMapping("", consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE))
    fun delete(@RequestBody(required = true) book: DeleteBookVM): ResponseEntity<String>
}