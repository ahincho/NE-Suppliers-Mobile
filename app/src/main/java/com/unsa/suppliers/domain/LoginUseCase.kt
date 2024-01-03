package com.unsa.suppliers.domain

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor (
    private val repository: SupplierRepository
) {
    suspend operator fun invoke(loginRequest: LoginRequest): LoginResponse? {
        return repository.attemptLogin(loginRequest)
    }
}