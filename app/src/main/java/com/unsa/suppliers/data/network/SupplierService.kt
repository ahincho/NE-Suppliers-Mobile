package com.unsa.suppliers.data.network

import com.unsa.suppliers.core.RetrofitHelper
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse

class SupplierService {
    private val retrofit = RetrofitHelper.getRetrofit()
    suspend fun login(loginRequest: LoginRequest): LoginResponse? {
        val response = retrofit.create(SupplierApiClient::class.java).login(loginRequest)
        return response.body()
    }
}