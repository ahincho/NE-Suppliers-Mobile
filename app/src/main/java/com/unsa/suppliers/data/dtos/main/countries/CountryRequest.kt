package com.unsa.suppliers.data.dtos.main.countries

import com.google.gson.annotations.SerializedName

data class CountryRequest (
    @SerializedName("name") val name: String
)