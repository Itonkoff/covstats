package com.example.covstats.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covstats.data.room.models.Statistic

@Database(entities = [Statistic::class], version = 1)
abstract class Db : RoomDatabase() {
    abstract fun statisticDao(): Dao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: Db? = null

        fun getDatabase(context: Context): Db {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Db::class.java,
                    "word_database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}