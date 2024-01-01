package com.unsa.suppliers.data.network

import com.unsa.suppliers.core.Constants.Companion.SUPPLIER_AUTH_ENDPOINT
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SupplierApiClient {
    @POST("${SUPPLIER_AUTH_ENDPOINT}/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}