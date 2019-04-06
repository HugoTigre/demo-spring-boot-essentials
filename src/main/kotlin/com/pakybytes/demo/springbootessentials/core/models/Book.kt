package com.pakybytes.demo.springbootessentials.core.models

data class Book(
        val title: String,
        val year: Int,
        val id: Int? = null
)