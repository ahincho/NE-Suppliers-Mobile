package com.unsa.suppliers.domain.main.categories

import com.unsa.suppliers.data.SupplierRepository
import javax.inject.Inject

class DisableCategoryUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int) {
        supplierRepository.attemptDisableCategory(id)
    }
}