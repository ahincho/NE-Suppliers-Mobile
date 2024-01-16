package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import javax.inject.Inject

class GetCategoryByIdUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int): CategoryResponse {
        return supplierRepository.attemptGetCategoryById(id) ?: CategoryResponse(0, "Error", "Error")
    }
}