package com.unsa.suppliers.domain.main.countries

import com.unsa.suppliers.data.repositories.CountryRepository
import javax.inject.Inject

class DeleteCountryUseCase @Inject constructor (
    private val countryRepository: CountryRepository
) {
    suspend operator fun invoke(id: Int) {
        countryRepository.attemptDeleteCountry(id)
    }
}