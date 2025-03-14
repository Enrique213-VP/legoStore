package com.svape.legostore.repository

import com.google.gson.GsonBuilder
import com.svape.legostore.data.model.store.ProductsList
import com.svape.legostore.untils.RequestConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {

    @GET("all-products")
    suspend fun getProduct(): ProductsList
}

object RetrofitClient{


    val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(RequestConstants.baseURL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build().create(WebService::class.java)
    }
}