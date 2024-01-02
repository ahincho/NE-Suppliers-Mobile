package com.unsa.suppliers.data.network

import com.unsa.suppliers.core.RetrofitHelper
import com.unsa.suppliers.data.dtos.auth.LoginRequest
import com.unsa.suppliers.data.dtos.auth.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
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
}