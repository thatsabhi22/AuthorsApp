package com.example.authorsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        fun getRetrofitInstance():Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://gutendex.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}