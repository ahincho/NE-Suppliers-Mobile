package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.data.repositories.CountryRepository
import javax.inject.Inject

class GetCountryByIdUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(id: Int): CountryResponse {
        return countryRepository.attemptGetCountryById(id) ?: CountryResponse(0, "Error", "Error")
    }
}