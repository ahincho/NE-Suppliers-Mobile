package com.unsa.suppliers.data.network.clients

import com.unsa.suppliers.core.Constants.Companion.COUNTRY_ENDPOINT
import com.unsa.suppliers.data.dtos.main.countries.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CountryApiClient {
    @GET(COUNTRY_ENDPOINT)
    suspend fun getAllCountries(): Response<MutableList<CountryResponse>>
    @GET("${COUNTRY_ENDPOINT}/{id}")
    suspend fun getCountryById(@Path("id") id: Int): Response<CountryResponse>
    @POST(COUNTRY_ENDPOINT)
    suspend fun createCountry(@Body countryRequest: CountryRequest): Response<CountryResponse>
    @PUT("${COUNTRY_ENDPOINT}/{id}")
    suspend fun updateCountry(@Path("id") id: Int, @Body countryRequest: CountryRequest): Response<Unit>
    @DELETE("${COUNTRY_ENDPOINT}/{id}")
    suspend fun deleteCountry(@Path("id") id: Int): Response<Unit>
    @PATCH("${COUNTRY_ENDPOINT}/disable/{id}")
    suspend fun disableCountry(@Path("id") id: Int): Response<Unit>
    @PATCH("${COUNTRY_ENDPOINT}/enable/{id}")
    suspend fun enableCountry(@Path("id") id: Int): Response<Unit>
}