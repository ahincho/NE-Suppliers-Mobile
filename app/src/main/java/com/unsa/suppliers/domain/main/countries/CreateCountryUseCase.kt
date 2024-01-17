package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.dtos.main.countries.CountryRequest
import com.unsa.suppliers.data.repositories.CountryRepository
import javax.inject.Inject

class CreateCountryUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(countryRequest: CountryRequest) {
        countryRepository.attemptCreateCountry(countryRequest)
    }
}