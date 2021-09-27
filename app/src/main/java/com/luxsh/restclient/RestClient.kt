package com.luxsh.restclient


import com.github.simonpercic.oklog3.OkLogInterceptor
import com.google.gson.GsonBuilder
import com.luxsh.model.PostofficeListResponse


import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface RestClient {
    companion object {
        fun create(): RestClient {
            val okLogInterceptor = OkLogInterceptor.builder().build()
            val okHttpBuilder = OkHttpClient.Builder()
            // Define the interceptor, add authentication headers
/*
            val interceptor = Interceptor { chain ->
                val newRequest = chain.request().newBuilder().addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Content-type", "application/x-www-form-urlencoded")
                        .build()
                chain.proceed(newRequest)
            }
*/

            okHttpBuilder.connectTimeout(20, TimeUnit.SECONDS)
            okHttpBuilder.readTimeout(20, TimeUnit.SECONDS).build()
            okHttpBuilder.writeTimeout(20, TimeUnit.SECONDS)

            okHttpBuilder.addInterceptor(okLogInterceptor)
            val okHttpClient = okHttpBuilder.build()
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(WebServices.DOMAIN)
                .client(okHttpClient)
                .build()
            return retrofit.create(RestClient::class.java)
        }
    }

    @POST()
    fun GetOrder(@Url url: String): Observable<PostofficeListResponse>


}
