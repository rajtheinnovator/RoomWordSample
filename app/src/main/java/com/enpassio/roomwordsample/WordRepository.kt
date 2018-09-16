package com.enpassio.roomwordsample

import android.app.Application
import android.arch.lifecycle.LiveData
import java.util.concurrent.Executor


class WordRepository(application: Application,
                     val db: WordRoomDatabase = WordRoomDatabase.getDatabase(application)!!,
                     val mWordDao: WordDao = db.wordDao(),
                     val allWords: LiveData<List<Word>> = mWordDao.allWords) {

    private val mExecutor: Executor

    init {
        mExecutor = AppExecutors.instance.diskIO
    }

    fun insert(word: Word) {
        mExecutor.execute {
            mWordDao.insert(word)
        }
    }
}