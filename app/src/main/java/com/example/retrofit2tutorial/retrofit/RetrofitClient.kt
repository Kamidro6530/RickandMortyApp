package com.example.retrofit2tutorial.retrofit

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

   val instance by lazy {
       val retrofit = Retrofit.Builder()
           .baseUrl("https://rickandmortyapi.com/api/")
           .addConverterFactory(GsonConverterFactory.create())
           .addCallAdapterFactory(CoroutineCallAdapterFactory())

           .build()

       retrofit.create(RiMService::class.java)



   }


}