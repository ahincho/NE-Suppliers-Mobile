package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.repositories.CategoryRepository
import javax.inject.Inject

class GetCategoryByIdUseCase @Inject constructor (
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(id: Int): CategoryResponse {
        return categoryRepository.attemptGetCategoryById(id) ?: CategoryResponse(0, "Error", "Error")
    }
}