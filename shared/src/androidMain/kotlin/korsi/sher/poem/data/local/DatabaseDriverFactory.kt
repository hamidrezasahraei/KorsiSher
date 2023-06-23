package korsi.sher.poem.data.local

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import korsi.sher.database.PoemDatabase

actual class DatabaseDriverFactory (
    private val context: Context
){
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(
            schema = PoemDatabase.Schema,
            context = context,
            name = "poem.db"
        )
    }
}