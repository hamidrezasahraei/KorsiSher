package korsi.sher.poem.data.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import korsi.sher.database.PoemDatabase

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(
            schema = PoemDatabase.Schema,
            name = "poem.db"
        )
    }
}