package com.unsa.suppliers.data.network.services

import com.unsa.suppliers.data.dtos.main.suppliers.*
import com.unsa.suppliers.data.network.clients.SupplierApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SupplierService @Inject constructor (
    private val supplierApiClient: SupplierApiClient
) {
    suspend fun getSuppliers(): MutableList<SupplierResponse>? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.getAllSuppliers()
            response.body()
        }
    }
    suspend fun getSupplierById(id: Int): SupplierResponse? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.getSupplierById(id)
            response.body()
        }
    }
    suspend fun createSupplier(supplierRequest: SupplierRequest): SupplierResponse? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.createSupplier(supplierRequest)
            response.body()
        }
    }
    suspend fun updateSupplier(id: Int, supplierRequest: SupplierRequest) {
        return withContext(Dispatchers.IO) {
            supplierApiClient.updateSupplier(id, supplierRequest)
        }
    }
    suspend fun deleteSupplier(id: Int) {
        withContext(Dispatchers.IO) {
            supplierApiClient.deleteSupplier(id)
        }
    }
    suspend fun enableSupplier(id: Int) {
        withContext(Dispatchers.IO) {
            supplierApiClient.enableSupplier(id)
        }
    }
    suspend fun disableSupplier(id: Int) {
        withContext(Dispatchers.IO) {
            supplierApiClient.disableSupplier(id)
        }
    }
}