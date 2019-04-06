package com.pakybytes.demo.springbootessentials.core.models.log

data class HttpHeaderResponse(
        val status: Int?,
        val msg: String?,
        val header: Map<String, List<String>?>?
)