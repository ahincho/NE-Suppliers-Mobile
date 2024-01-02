package com.unsa.suppliers.data.network

import com.unsa.suppliers.core.Constants.Companion.SUPPLIER_AUTH_ENDPOINT
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import com.unsa.suppliers.data.dtos.auth.UserRequest
import com.unsa.suppliers.data.dtos.auth.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SupplierApiClient {
    @POST("${SUPPLIER_AUTH_ENDPOINT}/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
    @POST("${SUPPLIER_AUTH_ENDPOINT}/register")
    suspend fun register(@Body userRequest: UserRequest): Response<UserResponse>
}