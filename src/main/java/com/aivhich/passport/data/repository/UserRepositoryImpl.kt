package com.aivhich.passport.data.repository


import com.aivhich.passport.common.Result
import com.aivhich.passport.data.datasource.UserDao
import com.aivhich.passport.data.remote.dto.toUser
import com.aivhich.passport.data.remote.retrofit.ApiService
import com.aivhich.passport.domain.model.User
import com.aivhich.passport.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val api: ApiService
) : UserRepository {
    override suspend fun save(u: User) {
        dao.saveUser(u)
    }

    override suspend fun get(token: String): Result<User> {
        return try {
            val user = api.getUser("Bearer $token")
            Result.Success(user?.toUser() ?: dao.get())
        }catch (e:Throwable){
            Result.Error(e)
        }
    }

    override suspend fun delete() {
        dao.delete()
    }
}