package com.unsa.suppliers.data

import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.network.SupplierService

class SupplierRepository {
    private val api = SupplierService()
    suspend fun attemptLogin(loginRequest: LoginRequest): LoginResponse? {
        return api.login(loginRequest)
    }
}