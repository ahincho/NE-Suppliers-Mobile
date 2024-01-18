package com.unsa.suppliers.domain.auth

import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import com.unsa.suppliers.data.repositories.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor (
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(userRequest: UserRequest): UserResponse? {
        return authRepository.attemptRegister(userRequest)
    }
}