package com.example.authorsapp.api

import com.example.authorsapp.rawdata.Books
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/books")
    fun getBooksData(): Call<Books>
}