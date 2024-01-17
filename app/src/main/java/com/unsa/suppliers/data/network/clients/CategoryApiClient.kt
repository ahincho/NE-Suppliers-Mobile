package com.unsa.suppliers.data.network.clients

import com.unsa.suppliers.core.Constants.Companion.CATEGORY_ENDPOINT
import com.unsa.suppliers.data.dtos.main.categories.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CategoryApiClient {
    @GET(CATEGORY_ENDPOINT)
    suspend fun getAllCategories(): Response<MutableList<CategoryResponse>>
    @GET("${CATEGORY_ENDPOINT}/{id}")
    suspend fun getCategoryById(@Path("id") id: Int): Response<CategoryResponse>
    @POST(CATEGORY_ENDPOINT)
    suspend fun createCategory(@Body categoryRequest: CategoryRequest): Response<CategoryResponse>
    @PUT("${CATEGORY_ENDPOINT}/{id}")
    suspend fun updateCategory(@Path("id") id: Int, @Body categoryRequest: CategoryRequest): Response<Unit>
    @DELETE("${CATEGORY_ENDPOINT}/{id}")
    suspend fun deleteCategory(@Path("id") id: Int): Response<Unit>
    @PATCH("${CATEGORY_ENDPOINT}/disable/{id}")
    suspend fun disableCategory(@Path("id") id: Int): Response<Unit>
    @PATCH("${CATEGORY_ENDPOINT}/enable/{id}")
    suspend fun enableCategory(@Path("id") id: Int): Response<Unit>
}