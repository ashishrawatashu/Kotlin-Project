package com.app.petitionatlas.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object WebServiceFactory {

    private var api: WebService? = null
    val instance: WebService?
        get() {
            if (api == null) {


//                val httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
//                    .protocols(Arrays.asList(Protocol.HTTP_1_1));
//                val logging = HttpLoggingInterceptor()
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//                httpClient.addInterceptor(logging)
//                httpClient.readTimeout(120, TimeUnit.SECONDS)
//                httpClient.connectTimeout(120, TimeUnit.SECONDS)

//                val client: OkHttpClient = httpClient.build()
                val client: OkHttpClient? = UnsafeOkHttpClients.getUnsafeOkHttpClient()

                val gson: Gson = GsonBuilder()
                    .setLenient()
                    .create()
                val builder: Retrofit.Builder = Retrofit.Builder()
                    .baseUrl(WebUrl.BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))

                val retrofit: Retrofit = builder.build()
                api = retrofit.create(WebService::class.java)

            }
            return api
        }
}