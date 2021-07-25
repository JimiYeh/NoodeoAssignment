package com.cloudinteractive.noodeoassignment.network

import com.cloudinteractive.noodeoassignment.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {

    val apiService: ApiService
    var sessionToken: String? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder: Request.Builder
                if (sessionToken != null) {
                    requestBuilder = it.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader(
                            "X-Parse-Application-Id",
                            BuildConfig.PARSE_APPLICATION_ID
                        )
                        .addHeader("X-Parse-Session-Token", sessionToken)
                        .url(it.request().url())
                } else {
                    requestBuilder = it.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader(
                            "X-Parse-Application-Id",
                            BuildConfig.PARSE_APPLICATION_ID
                        )
                        .url(it.request().url())
                }
                it.proceed(requestBuilder.build())
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()



        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.HOST)
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClient)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


}