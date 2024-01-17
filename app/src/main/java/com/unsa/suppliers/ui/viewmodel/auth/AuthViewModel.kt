package com.unsa.suppliers.ui.viewmodel.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import com.unsa.suppliers.domain.auth.GetJwtTokenUseCase
import com.unsa.suppliers.domain.auth.LoginUseCase
import com.unsa.suppliers.domain.auth.RegisterUseCase
import com.unsa.suppliers.domain.auth.SaveJwtTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor (
    private val saveJwtTokenUseCase: SaveJwtTokenUseCase,
    private val getJwtTokenUseCase: GetJwtTokenUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    val token = MutableLiveData<String>()
    val userResponse = MutableLiveData<UserResponse>()
    fun login(username: String, password: String) {
        var response: LoginResponse?
        viewModelScope.launch {
            response = loginUseCase.invoke(LoginRequest(username, password))
            if (response != null) {
                token.postValue(response!!.token)
                saveJwtTokenUseCase(response!!.token)
            }
        }
    }
    fun register(userRequest: UserRequest) {
        var response: UserResponse?
        viewModelScope.launch {
            response = registerUseCase.invoke(userRequest)
            if (response != null) {
                userResponse.postValue(response!!)
            }
        }
    }
}