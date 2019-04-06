package com.pakybytes.demo.springbootessentials.data.utils

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.DefaultTransactionDefinition


/** This class is an easy to use abstraction to perform database
 * local transaction
 */
@Service
class RepoUtils(val jdbc: NamedParameterJdbcTemplate,
                val txManager: PlatformTransactionManager) {


    fun <T> withTransaction(txName: String, code: () -> T?): T? {

        val def = DefaultTransactionDefinition()
        def.setName(txName)
        def.propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED
        def.isolationLevel = TransactionDefinition.ISOLATION_READ_COMMITTED

        val status = txManager.getTransaction(def)

        try {
            val value = code()

            txManager.commit(status)

            return value

        } catch (ex: Exception) {
            txManager.rollback(status)
            throw ex
        }
    }

//    fun <T> withTransaction(code: () -> T): T? {
//
//        val conn = DataSourceUtils.getConnection(dataSource)
//
//        try {
//            conn.autoCommit = true
//
//            val value = code()
//
//            conn.commit()
//
//            return value
//
//        } catch (ex: Exception) {
//            conn.rollback()
//
//        } finally {
//            conn.autoCommit = true
//        }
//
//        return null
//    }

}