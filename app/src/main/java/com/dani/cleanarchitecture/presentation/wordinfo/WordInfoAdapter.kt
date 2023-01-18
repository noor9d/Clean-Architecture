package com.dani.cleanarchitecture.presentation.wordinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.cleanarchitecture.databinding.WordItemLayoutBinding
import com.dani.cleanarchitecture.domain.model.WordInfo

class WordInfoAdapter : RecyclerView.Adapter<WordInfoAdapter.ViewHolder>() {

    private val wordList = ArrayList<WordInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            WordItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val word = wordList[position]
        val stringBuilder = StringBuilder()
        stringBuilder.append("Phonetic: ").append(word.phonetic).append("\n")
        stringBuilder.append("Origin: ").append(word.origin).append("\n")

        holder.binding.apply {
            txtWord.text = word.word
            txtWordDetail.text = stringBuilder
        }


    }

    fun setData(wordList: List<WordInfo>) {
        this.wordList.clear()
        this.wordList.addAll(wordList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = wordList.size

    inner class ViewHolder(val binding: WordItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}