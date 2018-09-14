package com.enpassio.roomwordsample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData


class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: WordRepository

    internal val allWords: LiveData<List<Word>>

    init {
        mRepository = WordRepository(this.getApplication())
        allWords = mRepository.allWords
    }

    fun insert(word: Word) {
        mRepository.insert(word)
    }
}