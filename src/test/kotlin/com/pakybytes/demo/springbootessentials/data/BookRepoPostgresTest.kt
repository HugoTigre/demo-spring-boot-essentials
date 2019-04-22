package com.pakybytes.demo.springbootessentials.data

import common.IntegrationTest
import common.Mocks
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


@IntegrationTest
internal class BookRepoPostgresTest(@Autowired val bookRepo: BookRepoPostgres) {

    @Test
    fun insert() {
        val result = bookRepo.insert(Mocks.book)
        assertEquals(5, result)
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun queryById() {
    }
}