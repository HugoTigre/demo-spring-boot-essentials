package com.pakybytes.demo.springbootessentials.core.data

import com.pakybytes.demo.springbootessentials.core.models.Book

interface BookRepo {

    fun insert(book: Book): Int?

    fun update(book: Book): Int

    fun delete(book: Book): Int

    fun queryById(id: Int): Book?
}