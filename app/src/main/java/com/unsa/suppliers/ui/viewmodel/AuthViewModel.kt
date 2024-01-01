package com.unsa.suppliers.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.domain.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private var loginUseCase = LoginUseCase()
    val token = MutableLiveData<String>()
    fun login(username: String, password: String) {
        var response: LoginResponse?
        viewModelScope.launch(Dispatchers.IO) {
            response = loginUseCase.invoke(LoginRequest(username, password))
            if (response != null) {
                token.postValue(response!!.token)
            }
        }
    }
}