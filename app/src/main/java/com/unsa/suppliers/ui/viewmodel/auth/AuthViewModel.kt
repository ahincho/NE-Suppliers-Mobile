package com.unsa.suppliers.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import com.unsa.suppliers.domain.LoginUseCase
import com.unsa.suppliers.domain.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
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
    fun register(userRequest: UserRequest) {
        var response: UserResponse?
        viewModelScope.launch {
            response = registerUseCase.invoke(userRequest)
            withContext(Dispatchers.Main) {
            
            }
        }
    }
}