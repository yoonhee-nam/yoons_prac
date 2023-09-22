package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Image
import com.example.myapplication.model.SearchData

import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val tabTextList = listOf("Main","Like")
    var likeItems: ArrayList<SearchData> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewPager01.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout01, binding.viewPager01) { tab, pos ->
            tab.text = tabTextList[pos]
        }.attach()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        return true
    }



    fun addLikedImage(image : SearchData){
        if(!likeItems.contains(image)){
            Log.d("addLike","nyh get??")

            likeItems.add(image)
            Log.d("addLike","nyh ${likeItems.size}??")
        }
    }

    fun removeLikeImage(image: SearchData){
        likeItems.remove(image)
    }
}
