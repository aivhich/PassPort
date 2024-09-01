package com.aivhich.passport.presentation.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aivhich.passport.data.remote.dto.request.CheckExistRequest
import com.aivhich.passport.data.remote.dto.request.EmailVerifyRequest
import com.aivhich.passport.data.remote.dto.request.RegisterRequest
import com.aivhich.passport.data.remote.dto.request.Role
import com.aivhich.passport.data.remote.dto.response.CheckExistResponse
import com.aivhich.passport.data.remote.dto.response.StageResponse
import com.aivhich.passport.data.remote.dto.response.UserStage.COMPLETE_AUTH
import com.aivhich.passport.data.remote.dto.response.UserStage.DELETED_BECAUSE_NOT_VERIFIED
import com.aivhich.passport.data.remote.dto.response.UserStage.NOT_EXIST
import com.aivhich.passport.data.remote.dto.response.UserStage.NOT_VERIFIED
import com.aivhich.passport.presentation.states.AuthMistakesState
import com.aivhich.passport.R
import com.aivhich.passport.common.Event
import com.aivhich.passport.common.UiText
import com.aivhich.passport.domain.usecase.UserUseCase
import com.aivhich.passport.common.Result
import com.aivhich.passport.data.remote.dto.request.AuthenticationRequest
import com.aivhich.passport.presentation.states.AuthStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
    private val passwordPattern: String =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,25}\$"
    private val usernamePattern: String = "^(?=.*[a-zA-Z])\\w{3,25}\$"
    private val emailPattern: String = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val namesPattern: String = "([a-zA-Zа-яА-Я]+)"

    init {
        onStart()
    }

    private val _doRequest = MutableLiveData<Boolean>(false)
    val doRequest: LiveData<Boolean> get() = _doRequest
    fun setDoRequest(value: Boolean) {
        _doRequest.value = value
    }

    var accessToken: String = ""
    var refreshToken: String = ""

    private val _digitsCodeState = MutableLiveData<Boolean>(false)
    val digitsCodeState: LiveData<Boolean> get() = _digitsCodeState

    private val statusMessage = Channel<Event<UiText>>()
    val message = statusMessage.receiveAsFlow()

    private val _state = MutableLiveData<AuthStates>(AuthStates.Loading)
    val state: LiveData<AuthStates> get() = _state
    fun setState(value: AuthStates) {
        _state.value = value
    }


    private val errorChannel = Channel<AuthMistakesState>()
    val errors = errorChannel.receiveAsFlow()


    private suspend fun validateData(preSendCheck: Boolean): Boolean {
        var nicknameError: Int? = null
        var passwordError: Int? = null
        var emailError: Int? = null
        var nameError: Int? = null
        val authMistakesState = AuthMistakesState()

        if (preSendCheck) {
            when (val answer =
                userUseCase.isExistUseCase(CheckExistRequest(nickname.value!!, email.value!!))) {
                is Result.Success<CheckExistResponse> -> {
                    if (!answer.data.nickname) {
                        nicknameError = R.string.error_nickname_exist
                        authMistakesState.nicknameError = UiText.StringResource(nicknameError)
                    }
                    if (!answer.data.email) {
                        emailError = R.string.error_email_exist
                        authMistakesState.emailError = UiText.StringResource(emailError)
                    }
                }

                is Result.Error -> {}
            }
        } else {
            if ((name.value?.isNotEmpty() == true && name.value?.matches(namesPattern.toRegex()) == false)
                && surname.value?.isNotEmpty() == true && surname.value?.matches(namesPattern.toRegex()) == false
            ) {
                nameError = R.string.error_names
                authMistakesState.nameError = UiText.StringResource(nameError)
            }
            if (nickname.value?.isNotEmpty() == true && nickname.value?.matches(usernamePattern.toRegex()) == false) {
                nicknameError = R.string.field_incorrectly
                authMistakesState.nicknameError = UiText.StringResource(nicknameError)
            }
            if (email.value?.isNotEmpty() == true && email.value?.matches(emailPattern.toRegex()) == false) {
                emailError = R.string.email_pattern
                authMistakesState.emailError = UiText.StringResource(emailError)
            }
            if (password.value?.matches(passwordPattern.toRegex()) == false && password.value?.isNotEmpty() == true) {
                passwordError = R.string.password_pattern
                authMistakesState.passwordError = UiText.StringResource(passwordError)
            }
        }
        errorChannel.send(authMistakesState)
        return emailError == null && nicknameError == null
    }


    private val _surname = MutableLiveData<String>()
    val surname: LiveData<String> get() = _surname
    fun setSurname(value: String) {
        _surname.value = value
        viewModelScope.launch {
            validateData(false)
        }
    }

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> get() = _nickname
    fun setNickname(value: String) {
        _nickname.value = value
        viewModelScope.launch {
            validateData(false)
        }
    }

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> get() = _name
    fun setName(value: String) {
        _name.value = value
        viewModelScope.launch {
            validateData(false)
        }
    }

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email
    fun setEmail(value: String) {
        _email.value = value
        viewModelScope.launch {
            validateData(false)
        }
    }


    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password
    fun setPassword(value: String) {
        _password.value = value
        viewModelScope.launch {
            validateData(false)
        }
    }


    private val _code = MutableLiveData<String>()
    val code: LiveData<String> get() = _code
    fun setCode(value: String) {
        _code.value = value
        if (digitsCodeState.value == true) _digitsCodeState.value = false
        if (value.length == 6) {
            send()
        }
    }

    private fun onStart() {
        viewModelScope.launch {
            when (val answer: Result<StageResponse> = userUseCase.userStage()) {
                is Result.Success -> {
                    when (answer.data.stage) {
                        NOT_EXIST -> {
                            setState(AuthStates.SignUp)
                            statusMessage.send(Event(UiText.StringResource(R.string.usernotexist)))
                        }

                        DELETED_BECAUSE_NOT_VERIFIED -> {
                            setState(AuthStates.SignUp)
                            statusMessage.send(Event(UiText.StringResource(R.string.userwasdeleted)))
                        }

                        NOT_VERIFIED -> setState(AuthStates.EnterCode)
                        COMPLETE_AUTH -> {
                            when (val outtokens = userUseCase.userSignInWithToken()) {
                                is Result.Success -> {
                                    setState(
                                        AuthStates.Success(
                                            outtokens.data.accesssToken,
                                            outtokens.data.refreshToken
                                        )
                                    )
                                }

                                is Result.Error -> {
                                    setState(AuthStates.StartUp)//
                                }
                            }
                        }
                    }
                }

                is Result.Error -> {
                    setState(AuthStates.StartUp)
                }
            }
        }
    }

    private fun allSignupDataValid(): Boolean {
        return (email.value?.matches(emailPattern.toRegex()) == true &&
                nickname.value?.matches(usernamePattern.toRegex()) == true &&
                password.value?.matches(passwordPattern.toRegex()) == true &&
                nickname.value?.isNotBlank() == true &&
                email.value?.isNotBlank() == true &&
                password.value?.isNotBlank() == true)
    }

    private fun allLoginDataValid(): Boolean {
        return (email.value?.matches(emailPattern.toRegex()) == true &&
                password.value?.matches(passwordPattern.toRegex()) == true &&
                email.value?.isNotBlank() == true &&
                password.value?.isNotBlank() == true)
    }

    fun send() {
        viewModelScope.launch {
            if (state.value == AuthStates.SignUp) {
                if (allSignupDataValid()) {
                    if (validateData(true)) {
                        setDoRequest(true)

                        val answer = userUseCase.userSignupUseCase(
                            RegisterRequest(
                                name = name.value.toString(),
                                surname = surname.value.toString(),
                                username = nickname.value.toString(),
                                email = email.value.toString(),
                                password = password.value.toString(),
                                role = Role.USER
                            )
                        )
                        when (answer) {
                            is Result.Error -> {
                                statusMessage.send(Event(UiText.StringResource(R.string.error_happend)))
                                setDoRequest(false)
                            }

                            is Result.Success -> {
                                setDoRequest(false)
                                accessToken = answer.data.accesssToken
                                refreshToken = answer.data.refreshToken
                                setState(AuthStates.EnterCode)
                            }
                        }
                    }
                } else statusMessage.send(
                    Event(UiText.StringResource(R.string.pleasefillfields))
                )
            } else if (state.value == AuthStates.Login) {
                if (allLoginDataValid()) {
                    setDoRequest(true)
                    val answer = userUseCase.userLogin(
                        AuthenticationRequest(
                            email = email.value.toString(),
                            password = password.value.toString(),
                        )
                    )
                    when (answer) {
                        is Result.Error -> {
                            Log.d("out", answer.throwable.message.toString());
                            statusMessage.send(Event(UiText.StringResource(R.string.error_happend)))
                            setDoRequest(false)
                        }

                        is Result.Success -> {
                            setDoRequest(false)
                            accessToken = answer.data.accesssToken
                            refreshToken = answer.data.refreshToken
                            setState(AuthStates.Success(accessToken, refreshToken))
                        }

                    }
                }
            } else if (state.value == AuthStates.EnterCode) {
                when (val answer = userUseCase.userVerifyEmailUseCase(
                    EmailVerifyRequest(
                        accessToken = "",
                        email = email.value.toString(),
                        code = code.value!!.toInt()
                    )
                )) {
                    is Result.Success -> {
                        if (answer.data.isValid) {
                            setState(AuthStates.Success(accessToken, refreshToken))
                        } else {
                            _digitsCodeState.value = true
                            statusMessage.send(Event(UiText.StringResource(R.string.error_code)))
                        }
                    }

                    is Result.Error -> {
                        statusMessage.send(Event(UiText.StringResource(R.string.error_happend)))
                    }
                }
            }
        }
    }
}