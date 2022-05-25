package com.breaktime.lab1.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addResult(result: ResultEntity)

    @Query("SELECT * FROM results_database ORDER BY time")
    fun readAllData(): Flow<List<ResultEntity>>

    @Query("SELECT DISTINCT username FROM results_database")
    fun readAllNames(): Flow<List<String>>

    @Query("SELECT * FROM results_database ORDER BY time DESC LIMIT 1")
    fun getLast(): ResultEntity

    @Query("SELECT * FROM results_database ORDER BY satiety DESC LIMIT 1")
    fun getMaxSatiety(): ResultEntity
}