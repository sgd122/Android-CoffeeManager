/*
 * Created by Lee Oh Hyoung on 2019-09-14..
 */
package com.dnd.killcaffeine.di

import com.dnd.killcaffeine.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val baseUrl = "http://52.78.118.71:8080"
private const val CONNECT_TIMEOUT = 15L
private const val WRITE_TIMEOUT = 15L
private const val READ_TIMEOUT = 15L
private const val CACHE_SIZE = 10L * 1024 * 1024

val retrofitModule = module {

    single {
        Cache(androidApplication().cacheDir, CACHE_SIZE)
    }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single {
        OkHttpClient.Builder()
            .cache(get())
            .addNetworkInterceptor(get())
            .addInterceptor(get())
            .retryOnConnectionFailure(true)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single {
        Interceptor { chain ->
            chain.proceed(chain.request().newBuilder().build())
        }
    }

    single {
        HttpLoggingInterceptor().apply {
            when(BuildConfig.DEBUG){
                true -> run { level = HttpLoggingInterceptor.Level.BODY }
                else -> run { level = HttpLoggingInterceptor.Level.NONE }
            }
        }
    }
}