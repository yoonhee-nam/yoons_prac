package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.Image
import com.example.myapplication.model.SearchData

import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val tabTextList = listOf("Main","Like")

    private var imageList = ArrayList<SearchData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.viewPager01.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout01, binding.viewPager01) { tab, pos ->
            tab.text = tabTextList[pos]
        }.attach()
    }
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu., menu)
//        return true
//    }


    var likeimage: ArrayList<SearchData> = ArrayList()
    fun addLikedImage(image : SearchData){
        if(!likeimage.contains(image)){
            likeimage.add(image)
        }
    }

    fun removeLikeImage(image: SearchData){
        imageList.remove(image)
    }
}
