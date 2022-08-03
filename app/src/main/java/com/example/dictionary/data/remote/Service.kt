package com.example.dictionary.data.remote

import com.example.dictionary.data.remote.dto.WordDetailDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://api.dictionaryapi.dev"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface DictionaryApiService {
    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordDetail(
        @Path("word") word: String
    ): List<WordDetailDto>
}

object DictionaryApi{
    val retrofitService: DictionaryApiService by lazy {
        retrofit.create(DictionaryApiService::class.java)
    }
}