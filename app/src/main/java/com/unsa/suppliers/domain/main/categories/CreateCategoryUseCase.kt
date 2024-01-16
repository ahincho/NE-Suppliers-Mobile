package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.categories.CategoryRequest
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import javax.inject.Inject

class CreateCategoryUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(categoryRequest: CategoryRequest): CategoryResponse? {
        return supplierRepository.attemptCreateCategory(categoryRequest)
    }
}