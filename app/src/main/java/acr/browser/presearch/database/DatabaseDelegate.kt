package acr.browser.presearch.database

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A delegate that caches a [SQLiteDatabase] object for the consumer, reopening it whenever it is
 * provided if it has been closed between the last time it was accessed.
 */
private class DatabaseDelegate : ReadOnlyProperty<SQLiteOpenHelper, SQLiteDatabase> {

    private var sqLiteDatabase: SQLiteDatabase? = null

    override fun getValue(thisRef: SQLiteOpenHelper, property: KProperty<*>): SQLiteDatabase {
        return sqLiteDatabase?.takeIf(SQLiteDatabase::isOpen)
            ?: thisRef.writableDatabase.also { sqLiteDatabase = it }
    }

}

/**
 * Provides a delegate that caches a [SQLiteDatabase] object for the consumer, reopening it if it
 * has been closed.
 */
fun databaseDelegate(): ReadOnlyProperty<SQLiteOpenHelper, SQLiteDatabase> = DatabaseDelegate()
