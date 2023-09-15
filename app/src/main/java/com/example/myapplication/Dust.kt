package com.example.myapplication

import com.google.gson.annotations.SerializedName

// api에서는 response를 주는데 왜 변수에 response가 있고 DustResponse를 받지?
// NetworkInterface에서 getDust를 통해 Data 받아옴
data class Dust (val response: DustResponse)

data class DustResponse(
    //SerializedName은 들어온 값을 다른변수에 담을 때 사용
    @SerializedName("body")
    val dustBody: DustBody,
    @SerializedName("header")
    val dustHeader: DustHeader
)

data class DustBody(
    val totalCount: Int,
    @SerializedName("items")
    val dustItem: MutableList<DustItem>?,
    val pageNo: Int,
    val numOfRows: Int
)

data class DustHeader(
    val resultCode: String,
    val reesultMsg: String
)

data class DustItem(
    val so2Grade: String,
    val coFlag: String?,
    val khaiValue: String,
    val so2Value: String,
    val coValue: String,
    val pm25Flag: String?,
    val pm10Flag: String?,
    val o3Grade: String,
    val pm10Value: String,
    val khaiGrade: String,
    val pm25Value: String,
    val sidoName: String,
    val no2Flag: String?,
    val no2Grade: String,
    val o3Flag: String?,
    val pm25Grade: String,
    val so2Flag: String?,
    val dataTime: String,
    val coGrade: String,
    val no2Value: String,
    val stationName: String,
    val pm10Grade: String,
    val o3Value: String


)
