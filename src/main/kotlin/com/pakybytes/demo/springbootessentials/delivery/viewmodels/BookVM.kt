package com.pakybytes.demo.springbootessentials.delivery.viewmodels

import java.time.LocalDateTime

data class BookVM(
        val title: String,
        val year: Int,
        val requested: LocalDateTime? = LocalDateTime.now()
)