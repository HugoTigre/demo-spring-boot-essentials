package com.pakybytes.demo.springbootessentials.delivery.adapters


import com.pakybytes.demo.springbootessentials.core.models.Book
import com.pakybytes.demo.springbootessentials.core.models.status.MsgStatus
import com.pakybytes.demo.springbootessentials.core.models.status.StatusResult
import com.pakybytes.demo.springbootessentials.core.services.utils.JsonUtils
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class ResponseAdapter(private val jsonUtils: JsonUtils) {

    fun badRequest() =
            ResponseEntity.status(MsgStatus.BAD_REQUEST).body(jsonUtils.toJson(StatusResult(MsgStatus.BAD_REQUEST)))

    fun transformResponse(result: String?): ResponseEntity<String> {
        return transformResponse(
                if (result != null) StatusResult(MsgStatus.OK, "Title is $result") else StatusResult(MsgStatus.DAL_ERROR)
        )
    }

    fun transformResponse(result: BigInteger?): ResponseEntity<String> {
        return transformResponse(
                if (result != null) StatusResult(MsgStatus.OK, "Count is $result") else StatusResult(MsgStatus.DAL_ERROR)
        )
    }

    fun transformResponse(result: Boolean?): ResponseEntity<String> {
        return transformResponse(
                if (result != null && result) StatusResult(MsgStatus.OK) else StatusResult(MsgStatus.DAL_ERROR)
        )
    }

    fun transformResponse(book: Book?): ResponseEntity<String> {
        return transformResponse(
                if (book != null) listOf(book) else emptyList()
        )
    }

    fun transformResponse(result: List<Book>?): ResponseEntity<String> {
        return transformResponse(
                if (result != null) StatusResult(MsgStatus.OK, jsonUtils.toJson(result))
                else StatusResult(MsgStatus.DAL_ERROR)
        )
    }

    fun transformResponse(status: StatusResult): ResponseEntity<String> {
        return if (status.id != MsgStatus.OK) {
            ResponseEntity.ok(jsonUtils.toJson(status))
        } else {
            ResponseEntity.status(MsgStatus.BAD_REQUEST).body(jsonUtils.toJson(status))
        }
    }
}