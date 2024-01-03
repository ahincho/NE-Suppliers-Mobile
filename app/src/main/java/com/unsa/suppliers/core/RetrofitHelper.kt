package com.unsa.suppliers.core

import com.unsa.suppliers.core.Constants.Companion.SUPPLIER_API_REST_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SUPPLIER_API_REST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}