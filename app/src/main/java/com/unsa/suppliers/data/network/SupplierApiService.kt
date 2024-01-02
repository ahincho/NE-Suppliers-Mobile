package com.unsa.suppliers.data.network

import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SupplierApiService @Inject constructor (
    private val supplierApiClient: SupplierApiClient
) {
    suspend fun getSuppliers(): List<SupplierResponse>? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.getAllSuppliers()
            response.body()
        }
    }
}