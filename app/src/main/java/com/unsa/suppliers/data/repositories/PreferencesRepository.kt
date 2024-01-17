package com.unsa.suppliers.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.unsa.suppliers.data.database.DataStorePreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

const val DATA_STORE_NAME = "PREFERENCES"

val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

class PreferencesRepository (private val context: Context) : DataStorePreferences {
    companion object {
        val JWT = stringPreferencesKey("JWT")
    }
    override suspend fun saveJwtToken(jwt: String) {
        withContext(Dispatchers.IO) {
            context.datastore.edit { it[JWT] = jwt }
        }
    }
    override suspend fun getJwtToken(): String {
        val jwt: String
        withContext(Dispatchers.IO) {
            jwt = context.datastore.data.map { it[JWT] ?: "Nothing" } .first()
        }
        return jwt
    }
}