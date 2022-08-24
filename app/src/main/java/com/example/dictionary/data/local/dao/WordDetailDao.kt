package com.example.dictionary.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary.data.local.entities.WordDetailEntity

@Dao
interface WordDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordDetails(details: List<WordDetailEntity>)

    @Query("delete from worddetailentity where word in(:words)")
    suspend fun deleteWordDetails(words: List<String>)

    @Query("select * from worddetailentity where word = :word ")
    suspend fun getWordDetails(word: String): List<WordDetailEntity>
}