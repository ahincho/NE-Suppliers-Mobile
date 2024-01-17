package com.unsa.suppliers.data.repositories

import com.unsa.suppliers.data.dtos.main.countries.*
import com.unsa.suppliers.data.network.services.CountryService
import javax.inject.Inject

class CountryRepository @Inject constructor (
    private val countryService: CountryService
) {
    suspend fun attemptGetCountries(): MutableList<CountryResponse>? {
        return countryService.getAllCountries()
    }
    suspend fun attemptGetCountryById(id: Int): CountryResponse? {
        return countryService.getCountryById(id)
    }
    suspend fun attemptCreateCountry(countryRequest: CountryRequest): CountryResponse? {
        return countryService.createCountry(countryRequest)
    }
    suspend fun attemptDeleteCountry(id: Int) {
        countryService.deleteCountry(id)
    }
    suspend fun attemptEnableCountry(id: Int) {
        countryService.enableCountry(id)
    }
    suspend fun attemptDisableCountry(id: Int) {
        countryService.disableCountry(id)
    }
}