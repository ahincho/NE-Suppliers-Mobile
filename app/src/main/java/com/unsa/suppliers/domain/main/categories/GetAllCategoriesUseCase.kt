package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.repositories.CategoryRepository
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor (
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(): MutableList<CategoryResponse> {
        return categoryRepository.attemptGetCategories() ?: mutableListOf()
    }
}