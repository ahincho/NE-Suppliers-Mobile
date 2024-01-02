package com.unsa.suppliers.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val token = MutableLiveData<String>()
    fun login(username: String, password: String) {
        var response: LoginResponse?
        viewModelScope.launch {
            response = loginUseCase.invoke(LoginRequest(username, password))
            if (response != null) {
                token.postValue(response!!.token)
            }
        }
    }
}