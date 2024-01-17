package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import com.unsa.suppliers.data.repositories.CategoryRepository
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor (
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(categoryRequest: CategoryRequest): CategoryResponse? {
        return categoryRepository.attemptCreateCategory(categoryRequest)
    }
}