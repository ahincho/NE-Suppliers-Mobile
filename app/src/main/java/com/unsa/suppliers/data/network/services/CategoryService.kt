package com.unsa.suppliers.data.network.services

import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.network.clients.CategoryApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryService @Inject constructor (
    private val categoryApiClient: CategoryApiClient
) {
    suspend fun getAllCategories(): MutableList<CategoryResponse>? {
        return withContext(Dispatchers.IO) {
            val response = categoryApiClient.getAllCategories()
            response.body()
        }
    }
    suspend fun getCategoryById(id: Int): CategoryResponse? {
        return withContext(Dispatchers.IO) {
            val response = categoryApiClient.getCategoryById(id)
            response.body()
        }
    }
    suspend fun createCategory(categoryRequest: CategoryRequest): CategoryResponse? {
        return withContext(Dispatchers.IO) {
            val response = categoryApiClient.createCategory(categoryRequest)
            response.body()
        }
    }
    suspend fun updateCategory(id: Int, categoryRequest: CategoryRequest) {
        return withContext(Dispatchers.IO) {
            categoryApiClient.updateCategory(id, categoryRequest)
        }
    }
    suspend fun deleteCategory(id: Int) {
        return withContext(Dispatchers.IO) {
            categoryApiClient.deleteCategory(id)
        }
    }
    suspend fun disableCategory(id: Int) {
        return withContext(Dispatchers.IO) {
            categoryApiClient.disableCategory(id)
        }
    }
    suspend fun enableCategory(id: Int) {
        return withContext(Dispatchers.IO) {
            categoryApiClient.enableCategory(id)
        }
    }
}