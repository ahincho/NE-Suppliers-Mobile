package com.unsa.suppliers.data.repositories

import com.unsa.suppliers.data.dtos.auth.*
import com.unsa.suppliers.data.network.services.AuthService
import javax.inject.Inject

class AuthRepository @Inject constructor (
    private val authService: AuthService
) {
    suspend fun attemptLogin(loginRequest: LoginRequest): LoginResponse? {
        return authService.login(loginRequest)
    }
    suspend fun attemptRegister(userRequest: UserRequest): UserResponse? {
        return authService.register(userRequest)
    }
}