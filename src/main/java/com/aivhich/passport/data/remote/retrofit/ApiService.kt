package com.aivhich.passport.data.remote.retrofit

import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.applang.data.remote.dto.response.AuthenticationResponse
import com.aivhich.applang.data.remote.dto.response.EmailVerifyResponse
import com.aivhich.applang.data.remote.dto.request.RegisterRequest
import com.aivhich.passport.data.remote.dto.UserDto
import com.aivhich.applang.data.remote.dto.request.CheckExistRequest
import com.aivhich.applang.data.remote.dto.request.EmailVerifyRequest
import com.aivhich.applang.data.remote.dto.request.StageRequest
import com.aivhich.applang.data.remote.dto.response.CheckExistResponse
import com.aivhich.applang.data.remote.dto.response.StageResponse

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST


interface ApiService {
    @POST("/api/v1/auth/signup")
    @Headers("Content-Type: application/json")
    suspend fun register(@Body register: RegisterRequest): AuthenticationResponse


    @POST("/api/v1/auth/authenticate")
    @Headers("Content-Type: application/json")
    suspend fun authenticate(@Body register: AuthenticationRequest): AuthenticationResponse

    @POST("/api/v1/auth/userStage")
    @Headers("Content-Type: application/json")
    suspend fun userStage(@Body req: StageRequest): StageResponse

    @PATCH("/api/v1/users/change_password")
    @Headers("Content-Type: application/json")
    suspend fun changePassword(@Body register: AuthenticationRequest): AuthenticationResponse

    @POST("/api/v1/auth/isExistsCheck")
    suspend fun isExist(@Body checkExistRequest: CheckExistRequest): CheckExistResponse

    @GET("/api/v1/users/get")
    @Headers("Content-Type: application/json")
    suspend fun getUser(@Header("Authorization") authorization: String): UserDto

    @POST("/api/v1/auth/emailVerify")
    @Headers("Content-Type: application/json")
    suspend fun emailVerify(@Body emailVerifyRequest: EmailVerifyRequest
    ): EmailVerifyResponse

    @POST("/api/v1/auth/refresh_token")
    @Headers("Content-Type: application/json")
    suspend fun refreshToken()
    /*
    @POST("/api/v1/users/auth/users/")
    @Headers("Content-Type: application/json")
    fun signup(@Body user: CreateUserRequest): Call<CreateUserResponse?>


    @POST("/api/v1/users/auth/jwt/create/")
    @Headers("Content-Type: application/json")
    fun login(@Body loginUserRequest: LoginUserRequest): Call<LoginUserResponse?>

    @POST("/api/v1/users/auth/jwt/verify/")
    @Headers("Content-Type: application/json")
    fun validate(@Body validateTokenRequest: ValidateTokenRequest): Call<String?>


    @POST("/api/v1/users/auth/jwt/refresh/")
    @Headers("Content-Type: application/json")
    fun refresh(@Body refreshTokenRequest: RefreshTokenRequest): Call<RefreshTokenResponse?>


    @POST("/api/v1/users/auth/users")
    @Headers("Content-Type: application/json")
    fun forget(@Body user: User): Observable<String?>


    @POST("/api/v1/users/auth/users")
    @Headers("Content-Type: application/json")
    fun langTestResult(@Body user: User): Observable<String?>

    @POST("/api/v1/users/auth/users")
    @Headers("Content-Type: application/json")
    fun getNewWords(@Body user: User): Observable<String?>

    @POST("/api/v1/users/auth/users")
    @Headers("Content-Type: application/json")
    fun updateKnowWords(@Body user: User): Observable<String?>
-*/

    /*
        @POST("/api/v1/auth/signup")
        @Headers("Content-Type: application/json")
        fun createUser(@Body user: RegisterRequest): Observable<AuthenticationResponse?>

        @POST("/api/v1/swayt/create")
        @Headers("Content-Type: application/json")
        fun createSwayt(@Header("Authorization") authorization: String, @Body swaytCreateRequest: SwaytCreateRequest): Observable<String?>

        @GET("/api/v1/swayt/get_swayts_of_user")
        @Headers("Content-Type: application/json")
        fun getSwaytOfUser(@Header("Authorization") authorization: String): Observable<SwaytReadResponse?>

        @POST("/api/v1/auth/token-refresh")
        @Headers("Content-Type: application/json")
        fun refreshToken(@Header("Authorization") authorization: String): Observable<AuthenticationResponse>

        @POST("/api/server_info")
        @Headers("Content-Type: application/json")
        fun serverInfo(): Observable<ApiInfoAnswer?>


    //    @POST("/api/user/auth/token_test")
    //    fun tokenTest(@Body req: TestTokenRequest): Call<UserOpenDataResponse?>
    //
    //    @POST("/api/friends/friend_invite")
    //    fun friendInvite(@Header("Authorization") authorization: String, @Body invite: InviteRequest): Call<String?>
    //
    //    @POST("/api/friends/get_friends")
    //    @Headers("Content-Type: application/json")
    //    fun getFriends(@Header("Authorization") authorization: String):Call<UserListVo?>
    //
    //    @POST("/api/location/send_location")
    //    @Headers("Content-Type: application/json")
    //    fun sendLocation(@Header("Authorization") authorization: String, @Body locationRequest: LocationRequest):Call<BackgroundInfoResponse?>
    //
    //    @GET("/api/location/get_locations")
    //    @Headers("Content-Type: application/json")
    //    fun getLocations(@Header("Authorization") authorization: String):Call<LocationResponse?>*/
}