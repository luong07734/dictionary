package com.example.dictionary.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionary.data.local.DictionaryDatabase
import com.example.dictionary.data.local.DictionaryDatabase.Companion.getDatabase
import com.example.dictionary.domain.model.WordDetail
import com.example.dictionary.domain.repository.WordDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class WordDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val wordDetailRepository = WordDetailRepository(getDatabase(application))

    var WordDetailsList: Flow<List<WordDetail>> = flow{}

    fun onSearch(word: String){
        WordDetailsList = if(word.isBlank()){
            flow {  }
        } else{
            wordDetailRepository.getWordDetail(word)
        }
    }

    /**
     * View model factory
     */
    class WordDetailViewModelFactory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WordDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WordDetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct view model")
        }
    }
}

