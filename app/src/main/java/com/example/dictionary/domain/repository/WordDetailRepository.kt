package com.example.dictionary.domain.repository

import android.util.Log
import androidx.room.Database
import com.example.dictionary.data.local.DictionaryDatabase
import com.example.dictionary.data.remote.DictionaryApi
import com.example.dictionary.domain.model.WordDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class WordDetailRepository(private val database: DictionaryDatabase) {

    fun getWordDetail(word: String): Flow<List<WordDetail>> = flow {

        val wordDetails = database.wordDetailDao.getWordDetails(word).map { it.toWordDetail() }
        emit(wordDetails)
        try {
            val remoteWordDetails = DictionaryApi.retrofitService.getWordDetail(word)
            database.wordDetailDao.deleteWordDetails(remoteWordDetails.map { it.word })
            database.wordDetailDao.insertWordDetails(remoteWordDetails.map { it.toWordDetailEntity() })
        }
        catch (e: HttpException){
            Log.i("repo",e.message())
            emit(wordDetails)

        }catch (e: IOException){
            Log.i("repo",e.message.toString())
            emit(wordDetails)
        }

        val newWordDetail = database.wordDetailDao.getWordDetails(word).map { it.toWordDetail() }
        emit(newWordDetail)
    }
}