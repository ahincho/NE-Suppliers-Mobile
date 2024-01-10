package com.unsa.suppliers.domain.main.suppliers

import com.unsa.suppliers.data.SupplierRepository
import javax.inject.Inject

class DeleteSupplierUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int) {
        supplierRepository.attemptDeleteSupplier(id)
    }
}