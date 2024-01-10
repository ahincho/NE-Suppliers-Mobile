package com.unsa.suppliers.data.network.interceptors

import android.util.Log
import com.unsa.suppliers.data.PreferencesRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor (
    private val preferencesRepository: PreferencesRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = runBlocking { preferencesRepository.getJwtToken() }
        request.addHeader("Authorization", "Bearer $token")
        return chain.proceed(request.build())
    }
}