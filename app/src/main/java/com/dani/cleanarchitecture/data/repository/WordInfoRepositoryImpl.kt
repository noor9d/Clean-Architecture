package com.dani.cleanarchitecture.data.repository

import com.dani.cleanarchitecture.core.ApiStates
import com.dani.cleanarchitecture.data.local.dao.WordInfoDao
import com.dani.cleanarchitecture.data.remote.DictionaryApi
import com.dani.cleanarchitecture.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WordInfoRepositoryImpl @Inject constructor(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {

    override fun getWordInfo(word: String) = flow {
        emit(ApiStates.Loading())

        val wordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(ApiStates.Loading(data = wordInfo))

        try {
            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordInfo(remoteWordInfo.map { it.word!! })
            dao.insertWordInfo(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(
                ApiStates.Error(
                    message = "Oops, something went wrong!",
                    data = wordInfo
                )
            )
        } catch (e: IOException) {
            emit(
                ApiStates.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = wordInfo
                )
            )
        }

        val newWordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(ApiStates.Success(newWordInfo))
    }
}