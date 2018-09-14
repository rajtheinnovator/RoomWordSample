package com.enpassio.roomwordsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class WordListAdapter(val context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {


    override fun onBindViewHolder(holder: WordListAdapter.WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.wordItemView.text = current.word
            Log.v("my_tag", "current.word is: "+current.word)
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.text = "No Word"
        }
    }

    // Cached copy of words

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).

    override fun getItemCount(): Int {
        return if (mWords != null)
            mWords!!.size
        else
            0
    }


    private var mWords: List<Word>? = null

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)

    }

    init {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.recyclerview_item, parent, false)

        return WordViewHolder(view)
    }

    internal fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }
}