package com.aivhich.passport.domain.model

import androidx.room.Entity
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import java.sql.Time
import java.sql.Timestamp
import java.util.UUID

@Entity
data class User(
    @PrimaryKey
    val id: UUID,
    val name:String,
    val surname:String,
    val code:String,
    val email:String,
    val isEmailVerified:Boolean,
)