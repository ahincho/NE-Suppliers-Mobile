package com.unsa.suppliers.domain

import com.unsa.suppliers.data.SupplierRepository
import javax.inject.Inject

class InactivateSupplierUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int) {
        supplierRepository.attemptInactivateSupplier(id)
    }
}