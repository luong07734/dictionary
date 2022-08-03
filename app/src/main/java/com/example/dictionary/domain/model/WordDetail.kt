package com.example.dictionary.domain.model

import com.example.dictionary.data.remote.dto.LicenseDto
import com.example.dictionary.data.remote.dto.MeaningDto
import com.example.dictionary.data.remote.dto.PhoneticDto

data class WordDetail(
//    val license: LicenseDto,
    val meanings: List<Meaning>,
//    val phonetic: String,
    val phonetics: List<Phonetic>,
//    val sourceUrls: List<String>,
    val word: String
)
