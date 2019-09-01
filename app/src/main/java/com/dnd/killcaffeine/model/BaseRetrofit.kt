/*
 * Created by Lee Oh Hyoung on 2019-08-10..
 */
package com.dnd.killcaffeine.model

import com.dnd.killcaffeine.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofit {
    companion object {
        const val BASE_URL = "http://52.78.118.71:8080"

        private fun retrofitInstance(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()

        fun <T> create(service: Class<T>) : T  = retrofitInstance().create(service)

        private fun createOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            when(BuildConfig.DEBUG){
                true -> run{interceptor.level = HttpLoggingInterceptor.Level.BODY}
                else -> run{interceptor.level = HttpLoggingInterceptor.Level.NONE}
            }

            return OkHttpClient.Builder().addNetworkInterceptor(interceptor).build()
        }
    }
}