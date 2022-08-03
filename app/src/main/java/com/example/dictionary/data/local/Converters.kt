package com.example.dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.dictionary.data.util.JsonParser
import com.example.dictionary.domain.model.Meaning
import com.example.dictionary.domain.model.Phonetic
import com.google.gson.reflect.TypeToken



@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
) {
    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>(){}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromPhoneticsJson(json: String): List<Phonetic> {
        return jsonParser.fromJson<ArrayList<Phonetic>>(
            json,
            object : TypeToken<ArrayList<Phonetic>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toPhoneticsJson(phonetics: List<Phonetic>): String {
        return jsonParser.toJson(
            phonetics,
            object : TypeToken<ArrayList<Phonetic>>(){}.type
        ) ?: "[]"
    }
}