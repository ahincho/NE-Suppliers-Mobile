package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(): MutableList<CountryResponse> {
        return supplierRepository.attemptGetCountries() ?: mutableListOf()
    }
}