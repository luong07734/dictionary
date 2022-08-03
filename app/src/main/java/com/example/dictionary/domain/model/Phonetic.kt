package com.example.dictionary.domain.model

import com.example.dictionary.data.remote.dto.LicenseDto

data class Phonetic(
    val audio: String,
//    val license: LicenseDto,
//    val sourceUrl: String,
    val text: String
)
