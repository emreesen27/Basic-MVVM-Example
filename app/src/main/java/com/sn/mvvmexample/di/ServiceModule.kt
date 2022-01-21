package com.sn.mvvmexample.di

import com.sn.mvvmexample.BuildConfig
import com.sn.mvvmexample.data.remote.TodoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * HTTP client Retrofit2
 * Reference: https://square.github.io/retrofit/
 */

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun provideInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideHttpClient() = OkHttpClient.Builder().apply {
        addInterceptor(provideInterceptor())
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideHttpClient())
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): TodoService = retrofit.create(TodoService::class.java)
}