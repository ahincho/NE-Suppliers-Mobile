package com.unsa.suppliers.data.network.services

import com.unsa.suppliers.data.dtos.main.countries.*
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
    suspend fun getCountryById(id: Int): CountryResponse? {
        return withContext(Dispatchers.IO) {
            val response = countryApiClient.getCountryById(id)
            response.body()
        }
    }
    suspend fun createCountry(countryRequest: CountryRequest): CountryResponse? {
        return withContext(Dispatchers.IO) {
            val response = countryApiClient.createCountry(countryRequest)
            response.body()
        }
    }
    suspend fun updateCountry(id: Int, countryRequest: CountryRequest) {
        return withContext(Dispatchers.IO) {
            countryApiClient.updateCountry(id, countryRequest)
        }
    }
    suspend fun deleteCountry(id: Int) {
        withContext(Dispatchers.IO) {
            countryApiClient.deleteCountry(id)
        }
    }
    suspend fun enableCountry(id: Int) {
        withContext(Dispatchers.IO) {
            countryApiClient.enableCountry(id)
        }
    }
    suspend fun disableCountry(id: Int) {
        withContext(Dispatchers.IO) {
            countryApiClient.disableCountry(id)
        }
    }
}