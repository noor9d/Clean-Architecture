package com.dani.cleanarchitecture.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dani.cleanarchitecture.domain.model.Meaning
import com.dani.cleanarchitecture.domain.model.WordInfo

@Entity(tableName = "tbl_word")
data class WordInfoEntity(
    val word: String,
    val phonetic: String,
    val origin: String,
    val meanings: List<Meaning>,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            word = word,
            origin = origin,
            phonetic = phonetic
        )
    }
}