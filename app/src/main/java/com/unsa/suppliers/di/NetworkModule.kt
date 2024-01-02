package com.unsa.suppliers.di

import com.unsa.suppliers.core.Constants
import com.unsa.suppliers.data.network.SupplierApiClient
import com.unsa.suppliers.data.network.SupplierAuthClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.SUPPLIER_API_REST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideSupplierAuthClient(retrofit: Retrofit): SupplierAuthClient {
        return retrofit.create(SupplierAuthClient::class.java)
    }
    @Singleton
    @Provides
    fun provideSupplierApiClient(retrofit: Retrofit): SupplierApiClient {
        return retrofit.create(SupplierApiClient::class.java)
    }
}