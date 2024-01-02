package com.unsa.suppliers.data.dtos.suppliers

import com.google.gson.annotations.SerializedName

data class SupplierResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("ruc") val ruc: String,
    @SerializedName("categoryId") val categoryId: Int,
    @SerializedName("categoryName") val categoryName: String,
    @SerializedName("countryId") val countryId: Int,
    @SerializedName("countryName") val countryName: String,
    @SerializedName("stateId") val stateId: Int,
    @SerializedName("stateName") val stateName: String
)