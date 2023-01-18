package com.dani.cleanarchitecture.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dani.cleanarchitecture.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfo(info: List<WordInfoEntity>)

    @Query("DELETE FROM tbl_word WHERE word IN(:words)")
    suspend fun deleteWordInfo(words: List<String>)

    @Query("SELECT * FROM tbl_word WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfo(word: String): List<WordInfoEntity>
}