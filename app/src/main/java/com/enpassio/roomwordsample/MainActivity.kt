package com.enpassio.roomwordsample

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private var mWordViewModel: WordViewModel? = null
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        var adapter  = WordListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        mWordViewModel?.allWords?.observe(this, object : Observer<List<Word>> {
            override fun onChanged(words: List<Word>?) {
                adapter.setWords(words!!)
            }
        })
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view -> addWord() }

    }

    private fun addWord() {

        val intent = Intent(this@MainActivity, NewWordActivity::class.java)
        startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(word = data!!.getStringExtra(NewWordActivity.EXTRA_REPLY))
            mWordViewModel?.insert(word)

        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }
}
