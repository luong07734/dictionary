package com.example.dictionary.data.remote.dto

import com.example.dictionary.domain.model.Meaning

data class MeaningDto(
    val antonyms: List<String>,
    val definitions: List<DefinitionDto>,
    val partOfSpeech: String,
    val synonyms: List<String>
){
    fun toMeaning(): Meaning{
        return Meaning(
            partOfSpeech = partOfSpeech,
            definitions = definitions.map { it.toDefinition() }
        )
    }
}