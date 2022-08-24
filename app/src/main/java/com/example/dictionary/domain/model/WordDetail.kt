package com.example.dictionary.domain.model



data class WordDetail(
//    val license: LicenseDto,
    val meanings: List<Meaning>,
//    val phonetic: String,
    val phonetics: List<Phonetic>?,
//    val sourceUrls: List<String>,
    val word: String
)
