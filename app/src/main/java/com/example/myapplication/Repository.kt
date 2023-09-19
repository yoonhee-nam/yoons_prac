package com.example.myapplication

import com.example.myapplication.api.Image_client
import com.example.myapplication.model.Image
import retrofit2.Call

class Repository {

    suspend fun searchImage(query : String, sort : String) : Call<Image> {
        return Image_client.apiService.searchImage(query = query, sort = sort, page = 1, apiKey = Constants.AUTH_HEADER, size = 5)
    }

}