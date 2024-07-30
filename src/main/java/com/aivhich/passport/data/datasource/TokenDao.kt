package com.aivhich.passport.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aivhich.applang.domain.model.Token

@Dao
interface TokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToken(token: Token)

    @Query("SELECT * FROM token")
    suspend fun getToken(): List<Token>


    @Query("DELETE FROM token")
    suspend fun deleteToken()
}