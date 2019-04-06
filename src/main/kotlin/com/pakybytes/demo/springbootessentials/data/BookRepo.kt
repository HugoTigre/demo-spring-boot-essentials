package com.pakybytes.demo.springbootessentials.data

import com.pakybytes.demo.springbootessentials.core.data.BookRepo
import com.pakybytes.demo.springbootessentials.core.models.Book
import com.pakybytes.demo.springbootessentials.data.BookRepoPostgresSql.DELETE_BOOK
import com.pakybytes.demo.springbootessentials.data.BookRepoPostgresSql.INSERT_BOOK
import com.pakybytes.demo.springbootessentials.data.BookRepoPostgresSql.QUERY_BOOK_BY_ID
import com.pakybytes.demo.springbootessentials.data.BookRepoPostgresSql.UPDATE_BOOK
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder


object BookRepoPostgresSql {

    const val QUERY_BOOK_BY_ID =
            "select * from book where id = :id"

    @JvmStatic
    val INSERT_BOOK = """
            insert into book
                (title, year)
            values
                (:title, :year)
            on conflict (id) do update
            set
                title = :title,
                year = :year
            """.trimIndent()

    @JvmStatic
    val UPDATE_BOOK = """
            update
                book
            set
                year = :year
            where
                id = :id
            """.trimIndent()

    @JvmStatic
    val DELETE_BOOK = """
            delete from
                book
            where
                id = :id
            """.trimIndent()
}

class BookRepoPostgres(val jdbc: NamedParameterJdbcTemplate) : BookRepo {

    override
    fun insert(book: Book): Int? {

        val keyHolder = GeneratedKeyHolder()

        jdbc.update(
                INSERT_BOOK,
                MapSqlParameterSource(mapOf(
                        "title" to book.title,
                        "year" to book.year
                )),
                keyHolder, arrayOf("id")
        )

        return keyHolder.key?.toInt()
    }

    override
    fun update(book: Book): Int {

        val rows = jdbc.update(
                UPDATE_BOOK,
                MapSqlParameterSource(mapOf(
                        "id" to book.id,
                        "year" to book.year
                ))
        )

        return rows
    }

    override
    fun delete(book: Book): Int {

        val rows = jdbc.update(
                DELETE_BOOK,
                MapSqlParameterSource(mapOf(
                        "id" to book.id
                ))
        )

        return rows
    }

    override
    fun queryById(id: Int): Book? {
        return jdbc.query(
                QUERY_BOOK_BY_ID,
                MapSqlParameterSource("id", id)
        ) { rs, _ ->
            Book(
                    rs.getString("title"),
                    rs.getInt("year")
            )
        }.firstOrNull()
    }

}