package com.unsa.suppliers.data.network.clients

import com.unsa.suppliers.core.Constants.Companion.AUTH_ENDPOINT
import com.unsa.suppliers.data.dtos.auth.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiClient {
    @POST("${AUTH_ENDPOINT}/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @POST("${AUTH_ENDPOINT}/register")
    suspend fun register(@Body userRequest: UserRequest): Response<UserResponse>
}