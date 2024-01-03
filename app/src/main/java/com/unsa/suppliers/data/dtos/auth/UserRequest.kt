package com.unsa.suppliers.data.dtos.auth

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)