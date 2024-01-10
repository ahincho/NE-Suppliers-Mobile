package com.unsa.suppliers.data.dtos.main.suppliers

import com.google.gson.annotations.SerializedName

data class SupplierResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("ruc") val ruc: String,
    @SerializedName("category") val categoryName: String,
    @SerializedName("country") val countryName: String,
    @SerializedName("state") val stateName: String
)