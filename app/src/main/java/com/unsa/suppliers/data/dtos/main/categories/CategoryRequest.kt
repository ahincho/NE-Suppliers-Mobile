package com.unsa.suppliers.data.dtos.main.categories

import com.google.gson.annotations.SerializedName

data class CategoryRequest (
    @SerializedName("name") val name: String
)