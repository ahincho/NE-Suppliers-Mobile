package com.unsa.suppliers.data.network

import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SupplierService @Inject constructor (
    private val supplierApiClient: SupplierApiClient
) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.login(loginRequest)
            response.body()
        }
    }
    suspend fun register(userRequest: UserRequest): UserResponse? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.register(userRequest)
            response.body()
        }
    }
}