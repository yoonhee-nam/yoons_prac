package com.example.myapplication.api

import com.example.myapplication.model.Image
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

//Retrofit Interface
//GET 요청을 하기 위한 인터페이스
interface Image_Interface {
    @GET("v2/search/imag")
    suspend fun searchImage(
        //인증 방식, 서비스 앱에서 REST API 키로 인증 요청
        @Header("Authorization") apiKey: String,
        //검색을 원하는 질의어
        @Query("query") query : String?,
        //결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy
        @Query("sort") sort : String?,
        //결과 페이지 번호, 1~50 사이의 값, 기본 값 1
        @Query("page") page : Int,
        //한 페이지에 보여질 문서 수, 1~80 사이의 값, 기본 값 80
        @Query("size") size : Int
    ) : Call<Image>
}