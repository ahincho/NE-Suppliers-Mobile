package com.unsa.suppliers.data.network

import com.unsa.suppliers.core.Constants.Companion.SUPPLIER_API_SUPPLIER_ENDPOINT
import com.unsa.suppliers.data.dtos.suppliers.SupplierResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface SupplierApiClient {
    @GET(SUPPLIER_API_SUPPLIER_ENDPOINT)
    suspend fun getAllSuppliers(): Response<MutableList<SupplierResponse>>
    @GET("${SUPPLIER_API_SUPPLIER_ENDPOINT}/{id}")
    suspend fun getSupplierById(@Path("id") id: Int): Response<SupplierResponse>
    @DELETE("$SUPPLIER_API_SUPPLIER_ENDPOINT/{id}")
    suspend fun deleteSupplier(@Path("id") id: Int)
    @PATCH("$SUPPLIER_API_SUPPLIER_ENDPOINT/{id}/reactivate")
    suspend fun reactivateSupplier(@Path("id") id: Int)
    @PATCH("$SUPPLIER_API_SUPPLIER_ENDPOINT/{id}/inactivate")
    suspend fun inactivateSupplier(@Path("id") id: Int)
}