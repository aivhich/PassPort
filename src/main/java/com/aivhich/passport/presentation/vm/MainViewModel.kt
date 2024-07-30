package com.aivhich.passport.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aivhich.passport.presentation.UserState

class MainViewModel() : ViewModel()  {
    private val _uiUserState = MutableLiveData<UserState>(UserState.NOT_AUTHED)
    val uiUserState: LiveData<UserState>
        get() = _uiUserState


}