package com.aivhich.passport.data.remote.dto.response

import com.google.gson.annotations.SerializedName

enum class UserStage {
    @SerializedName("NOT_EXIST")
    NOT_EXIST,

    @SerializedName("DELETED_BECAUSE_NOT_VERIFIED")
    DELETED_BECAUSE_NOT_VERIFIED,

    @SerializedName("NOT_VERIFIED")
    NOT_VERIFIED,

    @SerializedName("COMPLETE_AUTH")
    COMPLETE_AUTH
}

data class StageResponse(
    @SerializedName("stage")
    val stage: UserStage
)
