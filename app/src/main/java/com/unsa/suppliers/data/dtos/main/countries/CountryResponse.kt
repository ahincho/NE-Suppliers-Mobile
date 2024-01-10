package com.unsa.suppliers.data.dtos.main.countries

import com.google.gson.annotations.SerializedName

data class CountryResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("state") val state: String
)