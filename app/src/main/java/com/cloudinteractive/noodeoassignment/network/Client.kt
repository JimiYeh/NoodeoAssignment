package com.cloudinteractive.noodeoassignment.network

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
                            "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD"
                        )
                        .addHeader("X-Parse-Session-Token", sessionToken)
                        .url(it.request().url())
                } else {
                    requestBuilder = it.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader(
                            "X-Parse-Application-Id",
                            "vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD"
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
            .baseUrl("https://watch-master-staging.herokuapp.com")
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClient)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }


}