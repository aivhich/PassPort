package com.aivhich.passport.domain.repository

import com.aivhich.passport.domain.model.User

interface UserRepository {
    suspend fun save(u: User)
    suspend fun get(token:String): User
    suspend fun delete()
}