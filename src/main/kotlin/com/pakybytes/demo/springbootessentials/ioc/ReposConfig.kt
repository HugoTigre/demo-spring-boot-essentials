package com.pakybytes.demo.springbootessentials.ioc

import com.pakybytes.demo.springbootessentials.core.data.BookRepo
import com.pakybytes.demo.springbootessentials.data.BookRepoPostgres
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import javax.sql.DataSource

@Configuration
class ReposConfig {

    @Bean
    fun namedJdbcTemplate(dataSource: DataSource) =
            NamedParameterJdbcTemplate(dataSource)

    @Bean
    fun bookRepo(namedJdbc: NamedParameterJdbcTemplate): BookRepo =
            BookRepoPostgres(namedJdbc)

}