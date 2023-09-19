package com.example.myapplication.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Repository
import com.example.myapplication.model.Image
import kotlinx.coroutines.launch
import retrofit2.Call


class MainViewModel(private val repository : Repository) : ViewModel() {

    val myCustomPosts : MutableLiveData<Call<Image>> = MutableLiveData()

    fun searchImage(){
        viewModelScope.launch {
            val response = repository.searchImage("페이커","accuracy")
            myCustomPosts.value = response
        }
    }

}

