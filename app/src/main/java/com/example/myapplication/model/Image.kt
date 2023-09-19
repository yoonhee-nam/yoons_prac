package com.example.myapplication.model

import com.google.gson.annotations.SerializedName


//- 서버 통신시 request body 또는 response body에서 사용할 JSON형태의 모델 클래스 작성 -> Kotlin에서는 data class 형태로 작성!
//- 변수명은 원래 서버에서 사용하는 값과 똑같이 작성.
//- 만약 앱 내에서 다른 변수명으로 사용하고 싶다면 아래 코드처럼 ' @SerializedName("서버에서 변수명") val 앱내변수명:자료형 ' 을 사용.
data class Image(
    val documents: ArrayList<Documents>,
    val meta:Meta,
)
{
    data class Documents(
        val collection : String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl : String,
        @SerializedName("image_url")
        val imageUrl :	String,
        val width : Int,
        val height : Int,
        @SerializedName("display_sitename")
        val displaySitename : String,
        @SerializedName("doc_url")
        val docUrl : String,
        val datetime : String

    )
    data class Meta(
        @SerializedName("total_count")
        val totalCount: Int?,
        val pageablecount: Int?,
        @SerializedName("is_end")
        val isEnd: Boolean?
    )
}



//data class ImageSearchResponse(
//    @SerializedName("meta")
//    val metaData: Meta?,
//    )