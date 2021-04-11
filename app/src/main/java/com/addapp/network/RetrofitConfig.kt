package com.addapp.network

import com.addapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig{

    private val BASE_URL: String = BuildConfig.BASE_URL
    private const val HTTP_CONNECT_TIMEOUT: Long = 60000
    private const val HTTP_READ_TIMEOUT: Long = 60000
    private const val HTTP_WRITE_TIMEOUT: Long = 60000
    private lateinit var mService: ApiService
    private lateinit var retrofit: Retrofit

    fun initRetrofit(){
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.MILLISECONDS)

        val okHttpClient = client.build()

        val gson = GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

        mService = retrofit.create(ApiService::class.java)

    }

    fun getAppService(): ApiService {
        return mService
    }
}