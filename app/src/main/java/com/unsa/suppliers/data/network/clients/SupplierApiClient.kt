package com.unsa.suppliers.data.network.clients

import com.unsa.suppliers.core.Constants.Companion.SUPPLIER_ENDPOINT
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierRequest
import com.unsa.suppliers.data.dtos.main.suppliers.SupplierResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SupplierApiClient {
    @GET(SUPPLIER_ENDPOINT)
    suspend fun getAllSuppliers(): Response<MutableList<SupplierResponse>>
    @GET("${SUPPLIER_ENDPOINT}/{id}")
    suspend fun getSupplierById(@Path("id") id: Int): Response<SupplierResponse>
    @POST(SUPPLIER_ENDPOINT)
    suspend fun createSupplier(@Body supplierRequest: SupplierRequest): Response<SupplierResponse>
    @PUT("${SUPPLIER_ENDPOINT}/{id}")
    suspend fun updateSupplier(@Path("id") id: Int, @Body supplierRequest: SupplierRequest): Response<Unit>
    @DELETE("$SUPPLIER_ENDPOINT/{id}")
    suspend fun deleteSupplier(@Path("id") id: Int): Response<Unit>
    @PATCH("$SUPPLIER_ENDPOINT/enable/{id}")
    suspend fun enableSupplier(@Path("id") id: Int): Response<Unit>
    @PATCH("$SUPPLIER_ENDPOINT/disable/{id}")
    suspend fun disableSupplier(@Path("id") id: Int): Response<Unit>
}