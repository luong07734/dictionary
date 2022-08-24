package com.example.dictionary.data.remote.dto

import com.example.dictionary.domain.model.Phonetic

data class PhoneticDto(
    val audio: String?,
    val license: LicenseDto,
    val sourceUrl: String?,
    val text: String?
){
    fun toPhonetic(): Phonetic{
        return Phonetic(
            audio = audio,
            text = text
        )
    }
}