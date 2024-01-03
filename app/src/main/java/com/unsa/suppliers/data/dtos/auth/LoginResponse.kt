package com.unsa.suppliers.data.dtos.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("message") val message: String,
    @SerializedName("token") val token: String,
    @SerializedName("username") val username: String
)