package com.example.apptest1kt.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit2 {
    private lateinit var service: Service
    private val link:String="http://5e032d0763d08b0014a28d2b.mockapi.io"
    fun  connectRetrofit(): Service {
            val retrofit = Retrofit.Builder()
                .baseUrl(link)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            service = retrofit.create(Service::class.java)
        return service
    }
}