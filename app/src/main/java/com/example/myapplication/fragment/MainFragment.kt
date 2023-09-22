package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.Constants
import com.example.myapplication.MainActivity
import com.example.myapplication.api.Image_client.apiService
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.model.Image
import com.example.myapplication.model.SearchData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {


    private lateinit var binding: FragmentMainBinding
    private lateinit var context1: Context
    private lateinit var adapter: MainFragAdapter
    private lateinit var gridLayoutManager: StaggeredGridLayoutManager

    private var searchImage : ArrayList<SearchData> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context1 = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }

    }

    override fun onResume(){
        super.onResume()

        val main = activity as MainActivity

        var likeimage = main.likeItems

        for(i in 0..adapter.imageList.size-1){
            if(i == 0)
                for(j in 0..likeimage.size-1) {
                    if (likeimage[j].url != adapter.imageList[i].url) {
                        adapter.imageList[i].like = false
                    } else {
                        adapter.imageList[i].like = true
                        break
                    }
                }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        setupView()


        binding.button.setOnClickListener {
            Log.d("ButtonClick", "nyh Button clicked")
            val query = binding.editTextText.text.toString()
            fetchImageResults(query)
        }


        return binding.root
    }

    private fun setupView(){
        gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recycler.layoutManager = gridLayoutManager
        adapter = MainFragAdapter(context1)
        binding.recycler.adapter = adapter
        binding.recycler.itemAnimator = null
    }



    private fun fetchImageResults(query : String){
        apiService.searchImage(Constants.AUTH_HEADER, query, "recency",1,80).enqueue(object : Callback<Image?> {
            override fun onResponse(call:Call<Image?>, response: Response<Image?>){
                response.body()?.meta?.let { meta ->
                    if(meta.totalCount!! > 0) {
                        response.body()!!.documents.forEach {document ->
                            val title = document.displaySitename
                            val dateTime = document.datetime
                            val url = document.thumbnailUrl
                            searchImage.add(SearchData(title, dateTime, url))
                        }
                    }
                }
                adapter.imageList = searchImage
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<Image?>, t: Throwable) {
                Log.d("Search.f","nyh fail")
            }
        })
    }

}