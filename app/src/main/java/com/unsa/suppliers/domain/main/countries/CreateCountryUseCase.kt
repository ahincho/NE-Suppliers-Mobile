package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.SupplierRepository
import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import javax.inject.Inject

class CreateCountryUseCase @Inject constructor (
    private val supplierRepository: SupplierRepository
) {
    suspend operator fun invoke(countryRequest: CountryRequest) {
        supplierRepository.attemptCreateCountry(countryRequest)
    }
}