package com.breaktime.lab1.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking

class ResultRepository(private val resultDao: ResultDao) {
    var readAllData: Flow<List<ResultEntity>> = resultDao.readAllData()

    var readAllNames: Flow<List<String>> = resultDao.readAllNames()

    val maxSatiety: ResultEntity
        get() {
            var max: ResultEntity
            runBlocking(Dispatchers.IO) {
                max = resultDao.getMaxSatiety()
            }
            return max
        }


    suspend fun addResult(result: ResultEntity) = resultDao.addResult(result)

    fun getLastScore() = resultDao.getLast()
}