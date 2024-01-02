package com.unsa.suppliers.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.unsa.suppliers.data.database.DataStorePreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

const val DATA_STORE_NAME = "PREFERENCES"

val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class PreferencesRepository (private val context: Context) : DataStorePreferences {
    companion object {
        val JWT = stringPreferencesKey("JWT")
    }
    override suspend fun saveJwtToken(jwt: String) {
       context.datastore.edit { it[JWT] = jwt }
    }
    override suspend fun getJwtToken(): String {
        return context.datastore.data.map { it[JWT] ?: "Nothing" }.first()
    }
}