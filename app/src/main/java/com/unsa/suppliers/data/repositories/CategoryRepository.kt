package com.unsa.suppliers.data.repositories

import com.unsa.suppliers.data.dtos.main.categories.*
import com.unsa.suppliers.data.network.services.CategoryService
import javax.inject.Inject

class CategoryRepository @Inject constructor (
    private val categoryService: CategoryService
) {
    suspend fun attemptGetCategories(): MutableList<CategoryResponse>? {
        return categoryService.getAllCategories()
    }
    suspend fun attemptGetCategoryById(id: Int): CategoryResponse? {
        return categoryService.getCategoryById(id)
    }
    suspend fun attemptCreateCategory(categoryRequest: CategoryRequest): CategoryResponse? {
        return categoryService.createCategory(categoryRequest)
    }
    suspend fun attemptDeleteCategory(id: Int) {
        categoryService.deleteCategory(id)
    }
    suspend fun attemptEnableCategory(id: Int) {
        categoryService.enableCategory(id)
    }
    suspend fun attemptDisableCategory(id: Int) {
        categoryService.disableCategory(id)
    }
}