package com.unsa.suppliers.data.network

import android.util.Log
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SupplierApiService @Inject constructor (
    private val supplierApiClient: SupplierApiClient
) {
    suspend fun getSuppliers(): MutableList<SupplierResponse>? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.getAllSuppliers()
            Log.d("SUPPLIER SERVICE", (response.body() ?: "No Auth").toString())
            response.body()
        }
    }
    suspend fun getSupplierById(id: Int): SupplierResponse? {
        return withContext(Dispatchers.IO) {
            val response = supplierApiClient.getSupplierById(id)
            response.body()
        }
    }
    suspend fun deleteSupplier(id: Int) {
        return withContext(Dispatchers.IO) {
            supplierApiClient.deleteSupplier(id)
        }
    }
    suspend fun reactivateSupplier(id: Int) {
        return withContext(Dispatchers.IO) {
            supplierApiClient.reactivateSupplier(id)
        }
    }
    suspend fun inactivateSupplier(id: Int) {
        return withContext(Dispatchers.IO) {
            supplierApiClient.inactivateSupplier(id)
        }
    }
}