package common

import com.pakybytes.demo.springbootessentials.core.models.Book
import com.pakybytes.demo.springbootessentials.core.models.status.StatusResult

object Mocks {

    val book = Book(
            "Attack of the 90's",
            2019,
            1
    )

    val statusOk = StatusResult(200)
}