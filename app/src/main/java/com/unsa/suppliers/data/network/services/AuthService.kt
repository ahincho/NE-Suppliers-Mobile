package com.unsa.suppliers.data.network.services

import com.unsa.suppliers.data.dtos.auth.*
import com.unsa.suppliers.data.network.clients.AuthApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthService @Inject constructor (
    private val authApiClient: AuthApiClient
) {
    suspend fun login(loginRequest: LoginRequest): LoginResponse? {
        return withContext(Dispatchers.IO) {
            val response = authApiClient.login(loginRequest)
            response.body()
        }
    }
    suspend fun register(userRequest: UserRequest): UserResponse? {
        return withContext(Dispatchers.IO) {
            val response = authApiClient.register(userRequest)
            response.body()
        }
    }
}