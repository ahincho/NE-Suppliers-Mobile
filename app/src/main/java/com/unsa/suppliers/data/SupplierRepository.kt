package com.unsa.suppliers.data

import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import com.unsa.suppliers.data.network.SupplierService
import javax.inject.Inject

class SupplierRepository @Inject constructor (
    private val supplierService: SupplierService
) {
    suspend fun attemptLogin(loginRequest: LoginRequest): LoginResponse? {
        return supplierService.login(loginRequest)
    }
    suspend fun attemptRegister(userRequest: UserRequest): UserResponse? {
        return supplierService.register(userRequest)
    }
}