package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import javax.inject.Inject

class GetCountryByIdUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(id: Int): CountryResponse {
        return supplierRepository.attemptGetCountryById(id) ?: CountryResponse(0, "Error", "Error")
    }
}