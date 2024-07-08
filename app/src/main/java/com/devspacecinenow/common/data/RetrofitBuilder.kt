package com.devspacecinenow.common.data

import com.devspacecinenow.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://api.themoviedb.org/3/movie/"

object RetrofitBuilder {

    private val httpClient : OkHttpClient
        get() {
            val clientBuilder = OkHttpClient.Builder()
            val token = BuildConfig.API_Key

            clientBuilder.addInterceptor{ chain ->
                val original : Request = chain.request()
                val requestBuilder : Request.Builder = original.newBuilder()
                    .header("Authorization","Bearer $token")
                val request : Request = requestBuilder.build()
                chain.proceed(request)


            }
            return clientBuilder.build()
        }

    val retofitInstance : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}