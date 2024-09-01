package com.aivhich.passport.di

import android.app.Application
import androidx.room.Room
import com.aivhich.passport.data.remote.retrofit.Common
import com.aivhich.passport.domain.repository.TokenRepository
import com.aivhich.passport.domain.usecase.UserUseCase
import com.aivhich.passport.data.datasource.TokenDatabase
import com.aivhich.passport.data.datasource.UserDatabase
import com.aivhich.passport.data.remote.retrofit.ApiService
import com.aivhich.passport.data.repository.TokenRepositoryImpl
import com.aivhich.passport.data.repository.UserRepositoryImpl
import com.aivhich.passport.domain.repository.UserRepository
import com.aivhich.passport.domain.usecase.UserSignupUseCase
import com.aivhich.passport.domain.usecase.UserStageUseCase
import com.aivhich.passport.domain.usecase.VerifyEmailUseCase
import com.aivhich.passport.domain.usecase.IsExistUseCase
import com.aivhich.passport.domain.usecase.UserLoginUseCase
import com.aivhich.passport.domain.usecase.UserSignInWithToken
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.NAME_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase, api: ApiService): UserRepository {
        return UserRepositoryImpl(dao = db.userDao, api = api)
    }

    @Provides
    @Singleton
    fun provideTokenDatabase(app: Application): TokenDatabase {
        return Room.databaseBuilder(
            app,
            TokenDatabase::class.java,
            TokenDatabase.NAME_DATABASE
        ).fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideTokenRepository(api: ApiService, db:TokenDatabase): TokenRepository {
        return TokenRepositoryImpl(api, db.tokenDao)
    }


    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Common.retrofitService
    }

    @Provides
    @Singleton
    fun provideUserUseCase(repository: UserRepository,
                           tokenRepository: TokenRepository,
                           api: ApiService,
                           tokendb:TokenDatabase,
                           userdb: UserDatabase
    ): UserUseCase {
        return UserUseCase(
            userSignupUseCase = UserSignupUseCase(
                userRepository = repository,
                tokenRepository = tokenRepository,
                userDao = userdb.userDao
            ),
            userVerifyEmailUseCase =
            VerifyEmailUseCase(
                apiService = api,
                userDao = userdb.userDao,
                dao=tokendb.tokenDao
            ),
            isExistUseCase = IsExistUseCase(api),
            userStage = UserStageUseCase(userDao = userdb.userDao, apiService = api),
            userLogin = UserLoginUseCase(
                userRepository = repository,
                tokenRepository = tokenRepository,
                userDao = userdb.userDao),
            userSignInWithToken = UserSignInWithToken(
                userRepository = repository,
                tokenRepository = tokenRepository,
                tokenDao = tokendb.tokenDao,
                userDao = userdb.userDao,
                api = api
            )
        )
    }
}