package com.unsa.suppliers.data.dtos.main.suppliers

import com.google.gson.annotations.SerializedName

data class SupplierRequest (
    @SerializedName("name") val name: String,
    @SerializedName("ruc") val ruc: String,
    @SerializedName("categoryId") val categoryId: Int,
    @SerializedName("countryId") val countryId: Int
)