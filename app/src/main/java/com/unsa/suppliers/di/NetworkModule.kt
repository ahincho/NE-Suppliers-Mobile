package com.unsa.suppliers.di

import com.unsa.suppliers.core.Constants
import com.unsa.suppliers.data.network.AuthInterceptor
import com.unsa.suppliers.data.network.SupplierApiClient
import com.unsa.suppliers.data.network.SupplierAuthClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.SUPPLIER_API_REST_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }
    @Singleton
    @Provides
    fun provideSupplierAuthClient(retrofitBuilder: Retrofit.Builder): SupplierAuthClient {
        return retrofitBuilder.build().create(SupplierAuthClient::class.java)
    }
    @Singleton
    @Provides
    fun provideSupplierApiClient(retrofit: Retrofit.Builder, okHttpClient: OkHttpClient): SupplierApiClient {
        return retrofit.client(okHttpClient).build().create(SupplierApiClient::class.java)
    }
}