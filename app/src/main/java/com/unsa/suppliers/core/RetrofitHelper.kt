package com.unsa.suppliers.core

import com.unsa.suppliers.core.Constants.Companion.API_REST_SERVICE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_REST_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}