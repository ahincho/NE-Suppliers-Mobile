package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.data.repositories.CategoryRepository
import javax.inject.Inject

class UpdateCategoryUseCase @Inject constructor (
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(id: Int, categoryRequest: CategoryRequest) {
        categoryRepository.attemptUpdateCategory(id, categoryRequest);
    }
}