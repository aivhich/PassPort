package com.aivhich.passport.data.repository


import android.util.Log
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.data.remote.retrofit.ApiService
import com.aivhich.passport.domain.model.User
import com.aivhich.passport.domain.repository.UserRepository
import com.aivhich.passport.data.remote.dto.toUser
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val api: ApiService
): UserRepository {
    override suspend fun save(u: User) {
        dao.saveUser(u)
    }

    override suspend fun get(token:String): User {
        Log.d("out", "getting user")
        val user = api.getUser("Bearer "+token).toUser() ?: return dao.get();
        return user
    }

    override suspend fun delete() {
        dao.delete()
    }
}