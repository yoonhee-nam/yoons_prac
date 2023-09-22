package com.example.myapplication.model

data class SearchData(
    var title: String,
    var dateTime: String,
    var url: String,
    var like: Boolean = false
)
