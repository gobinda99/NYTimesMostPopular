package com.gobinda.mvp.sample.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nytimes.sample.data.model.Data

/**
 * ORM Database Helper class to connect to the database
 * named flight-sample.db and its records
 */
@Database(entities = arrayOf(Data::class), version = 1)
abstract class NYNewsDatabase : RoomDatabase() {

    abstract fun flightEventDao(): NYNewDao

    companion object {

        @Volatile
        private var INSTANCE: NYNewsDatabase? = null

        fun getInstance(context: Context): NYNewsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: bindDatabase(context).also { INSTANCE = it }
            }

        private fun bindDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, NYNewsDatabase::class.java, "flight-sample.db")
                .build()
    }
}
