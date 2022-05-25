package com.breaktime.lab1.data

import android.content.Context
import androidx.room.*

@Database(entities = [ResultEntity::class], version = 2, exportSchema = false)
@TypeConverters(value = [Converters::class])
abstract class ResultDatabase : RoomDatabase() {
    abstract fun resultDao(): ResultDao

    companion object {
        @Volatile
        private var INSTANCE: ResultDatabase? = null
        fun getDatabase(context: Context): ResultDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ResultDatabase::class.java,
                    "results_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}