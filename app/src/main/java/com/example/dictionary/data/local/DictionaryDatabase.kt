package com.example.dictionary.data.local

import android.content.Context
import androidx.room.*
import com.example.dictionary.data.local.dao.WordDetailDao
import com.example.dictionary.data.local.entities.WordDetailEntity
import com.example.dictionary.data.util.GsonParser
import com.google.gson.Gson

//86a7738fbb364404959a0db11fa137a5
@Database( entities = [WordDetailEntity::class], version = 2, exportSchema = false)

@TypeConverters(Converters::class)

abstract class DictionaryDatabase : RoomDatabase() {
    abstract val wordDetailDao: WordDetailDao

    companion object {
        @Volatile
        private var INSTANCE: DictionaryDatabase? = null

        fun getDatabase(context: Context): DictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DictionaryDatabase::class.java,
                    "dictionary_database"
                )
                    .addTypeConverter(Converters(GsonParser(Gson())))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}