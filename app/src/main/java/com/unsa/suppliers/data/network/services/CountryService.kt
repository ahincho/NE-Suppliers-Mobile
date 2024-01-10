package com.unsa.suppliers.data.network.services

import com.unsa.suppliers.data.dtos.main.countries.CountryResponse
import com.unsa.suppliers.data.network.clients.CountryApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryService @Inject constructor (
    private val countryApiClient: CountryApiClient
) {
    suspend fun getAllCountries(): MutableList<CountryResponse>? {
        return withContext(Dispatchers.IO) {
            val response = countryApiClient.getAllCountries()
            response.body()
        }
    }
}