package com.unsa.suppliers.data

import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import com.unsa.suppliers.data.network.SupplierApiClient
import com.unsa.suppliers.data.network.SupplierApiService
import com.unsa.suppliers.data.network.SupplierAuthService
import javax.inject.Inject

class SupplierRepository @Inject constructor (
    private val supplierAuthService: SupplierAuthService,
    private val supplierApiService: SupplierApiService
) {
    suspend fun attemptLogin(loginRequest: LoginRequest): LoginResponse? {
        return supplierAuthService.login(loginRequest)
    }
    suspend fun attemptRegister(userRequest: UserRequest): UserResponse? {
        return supplierAuthService.register(userRequest)
    }
    suspend fun attemptGetSuppliers(): List<SupplierResponse>? {
        return supplierApiService.getSuppliers()
    }
}