package com.enpassio.roomwordsample

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask


class WordRepository(application: Application,
                     val db: WordRoomDatabase = WordRoomDatabase.getDatabase(application)!!,
                     val mWordDao: WordDao = db.wordDao(),
                     val allWords: LiveData<List<Word>> = mWordDao.allWords) {

    fun insert(word: Word) {
        insertAsyncTask(mWordDao).execute(word)
    }

    private class insertAsyncTask internal constructor(private val mAsyncTaskDao: WordDao) : AsyncTask<Word, Void, Void>() {

        override fun doInBackground(vararg params: Word): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}