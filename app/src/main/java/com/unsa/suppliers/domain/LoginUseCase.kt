package com.unsa.suppliers.domain

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse

class LoginUseCase {
    private val repository = SupplierRepository()
    suspend operator fun invoke(loginRequest: LoginRequest): LoginResponse? {
        return repository.attemptLogin(loginRequest)
    }
}