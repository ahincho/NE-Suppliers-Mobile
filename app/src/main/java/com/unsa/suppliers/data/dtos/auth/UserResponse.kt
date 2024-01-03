package com.unsa.suppliers.data.dtos.auth

import com.google.gson.annotations.SerializedName

data class UserResponse (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("username") val username: String
)