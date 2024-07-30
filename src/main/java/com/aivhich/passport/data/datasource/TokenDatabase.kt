package com.aivhich.passport.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aivhich.applang.domain.model.Token
import com.aivhich.passport.data.datasource.TokenDao


@Database(
    entities = [Token::class],
    version = 1,
)
abstract class TokenDatabase: RoomDatabase(){
    abstract val tokenDao: TokenDao

    companion object{
        const val NAME_DATABASE = "token"
    }
}