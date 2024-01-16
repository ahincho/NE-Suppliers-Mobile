package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.SupplierRepository
import javax.inject.Inject

class DeleteCountryUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int) {
        supplierRepository.attemptDeleteCountry(id)
    }
}