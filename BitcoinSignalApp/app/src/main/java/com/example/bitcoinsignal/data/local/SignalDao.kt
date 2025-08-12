package com.example.bitcoinsignal.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SignalDao {
    @Insert
    suspend fun insert(signal: SignalEntity)

    @Query("SELECT * FROM signals ORDER BY timestampMs DESC LIMIT 100")
    suspend fun recent(): List<SignalEntity>
}
