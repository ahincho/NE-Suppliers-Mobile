package com.unsa.suppliers.domain.auth

import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): LoginResponse? {
        return authRepository.attemptLogin(loginRequest)
    }
}