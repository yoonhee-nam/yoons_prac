package com.example.myapplication.api

import com.example.myapplication.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Image_client {
    const val IMAGE_BASE_URL = "http://dapi.kakao.com/"

     val apiService: Image_Interface
         get() = instance.create(Image_Interface::class.java)


    private val instance: Retrofit
        private get() {

            val gson =GsonBuilder().setLenient().create()

            return Retrofit.Builder()
                .baseUrl(Constants.IMAGE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
}
