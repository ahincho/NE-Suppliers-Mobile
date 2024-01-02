package com.unsa.suppliers.data.network

import com.unsa.suppliers.core.Constants.Companion.SUPPLIER_API_SUPPLIER_ENDPOINT
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import retrofit2.Response
import retrofit2.http.GET

interface SupplierApiClient {
    @GET(SUPPLIER_API_SUPPLIER_ENDPOINT)
    suspend fun getAllSuppliers(): Response<List<SupplierResponse>>
}