package com.example.dictionary.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.dictionary.data.local.Converters
import com.example.dictionary.domain.model.Meaning
import com.example.dictionary.domain.model.Phonetic
import com.example.dictionary.domain.model.WordDetail

@Entity
data class WordDetailEntity(
    val meanings: List<Meaning>,
    val phonetics: List<Phonetic>?,
    val word: String,
    @PrimaryKey val id: Int? = null
){
    fun toWordDetail(): WordDetail{
        return WordDetail(
            meanings = meanings,
            word = word,
            phonetics = phonetics
        )
    }
}
