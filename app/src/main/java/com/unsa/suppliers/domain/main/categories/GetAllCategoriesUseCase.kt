package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.categories.CategoryResponse
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(): MutableList<CategoryResponse> {
        return supplierRepository.attemptGetCategories() ?: mutableListOf()
    }
}