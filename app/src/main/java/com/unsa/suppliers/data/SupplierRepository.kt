package com.unsa.suppliers.data

import android.util.Log
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
    suspend fun attemptGetSuppliers(): MutableList<SupplierResponse>? {
        val suppliers = supplierApiService.getSuppliers()
        Log.d("SUPPLIER REPOSITORY", (suppliers ?: "Empty List").toString())
        return suppliers
    }
    suspend fun attemptGetSupplierById(id: Int): SupplierResponse? {
        return supplierApiService.getSupplierById(id)
    }
    suspend fun attemptDeleteSupplier(id: Int) {
        supplierApiService.deleteSupplier(id)
    }
    suspend fun attemptReactivateSupplier(id: Int) {
        supplierApiService.reactivateSupplier(id)
    }
    suspend fun attemptInactivateSupplier(id: Int) {
        supplierApiService.inactivateSupplier(id)
    }
}