package com.unsa.suppliers.data.database

interface DataStorePreferences {
    suspend fun saveJwtToken(jwt: String)
    suspend fun getJwtToken(): String?
}