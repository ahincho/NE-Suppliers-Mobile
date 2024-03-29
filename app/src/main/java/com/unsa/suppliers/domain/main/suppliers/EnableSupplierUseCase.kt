package com.unsa.suppliers.domain.main.suppliers

import com.unsa.suppliers.data.repositories.SupplierRepository
import javax.inject.Inject

class EnableSupplierUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int) {
        supplierRepository.attemptEnableSupplier(id)
    }
}