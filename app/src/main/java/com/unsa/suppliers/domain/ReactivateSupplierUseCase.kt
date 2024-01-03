package com.unsa.suppliers.domain

import com.unsa.suppliers.data.SupplierRepository
import javax.inject.Inject

class ReactivateSupplierUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int) {
        supplierRepository.attemptReactivateSupplier(id)
    }
}