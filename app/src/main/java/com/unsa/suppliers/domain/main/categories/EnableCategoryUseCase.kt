package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.repositories.CategoryRepository
import javax.inject.Inject

class EnableCategoryUseCase @Inject constructor (
    private val categoryRepository: CategoryRepository
) {
    suspend operator fun invoke(id: Int) {
        categoryRepository.attemptEnableCategory(id)
    }
}