package com.example.authorsapp.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Author::class, User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AuthorsDatabase : RoomDatabase() {
    abstract fun authorDAO(): AuthorDAO?
    abstract fun userDAO(): UserDAO?

    companion object {
        private val LOG_TAG = AuthorsDatabase::class.java.simpleName
        private val LOCK = Any()
        private const val DATABASE_NAME = "author.db"

        @Volatile
        private var sInstance: AuthorsDatabase? = null
        fun getInstance(context: Context): AuthorsDatabase? {
            if (sInstance == null) {
                synchronized(LOCK) {
                    if (sInstance == null) {
                        Log.d(
                            LOG_TAG,
                            "Creating new database instance"
                        )
                        sInstance = databaseBuilder(
                            context.applicationContext,
                            AuthorsDatabase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            Log.d(LOG_TAG, "Getting the database instance")
            return sInstance
        }
    }
}