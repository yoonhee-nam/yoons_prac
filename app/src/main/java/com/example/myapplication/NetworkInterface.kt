package com.example.myapplication

import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NetworkInterface {
    @GET("getCtprvnRltmMesureDnsty")
    //요청변수 Key값과 Value를 0HashMap 형태로 String 2개 getDust요청이 되면 Dust를 리턴받음
    suspend fun getDust(@QueryMap param: HashMap<String,String>):Dust
}