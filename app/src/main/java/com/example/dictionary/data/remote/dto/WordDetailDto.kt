package com.example.dictionary.data.remote.dto

import com.example.dictionary.domain.model.WordDetail

data class WordDetailDto(
    val license: LicenseDto,
    val meanings: List<MeaningDto>,
    val phonetic: String,
    val phonetics: List<PhoneticDto>,
    val sourceUrls: List<String>,
    val word: String
){
    fun toWordDetail(): WordDetail{
        return WordDetail(
            word = word,
            phonetics = phonetics.map { it.toPhonetic() },
            meanings = meanings.map { it.toMeaning() }
        )
    }
}