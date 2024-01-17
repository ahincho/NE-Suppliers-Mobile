package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.data.repositories.CountryRepository
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(): MutableList<CountryResponse> {
        return countryRepository.attemptGetCountries() ?: mutableListOf()
    }
}