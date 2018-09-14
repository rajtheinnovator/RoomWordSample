package com.enpassio.roomwordsample

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "word_table")
class Word(@PrimaryKey(autoGenerate = true)
           var id: Int = 1,
           @ColumnInfo(name = "word")
           val word: String)