package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.api.Image_client
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.model.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var mainFragAdapter: MainFragAdapter
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        recyclerView()
//        searchListener()

        var imageList = listOf<Image.Documents>()

        binding.button.setOnClickListener {
            Log.d("ButtonClick", "nyh Button clicked")
            val query = binding.editTextText.text.toString()
            initList(query)
            mainFragAdapter.setList(imageList)
            mainFragAdapter.notifyDataSetChanged()
        }

        binding.recycler.apply {
            adapter = mainFragAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        return binding.root
    }

    private fun recyclerView() {
        val gridLayout = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.recycler.layoutManager = gridLayout

        mainFragAdapter = MainFragAdapter(mContext)
        binding.recycler.adapter = mainFragAdapter
        //다른 item 깜빡거리는거 방지
        binding.recycler.itemAnimator = null
    }

//    private fun searchListener() {
//
//        binding.editTextText.setOnClickListener {
//            val query = binding.editTextText.text.toString()
//            if(query.isNotEmpty()) {
//                Utils.saveLastSearch(requireContext(), query)
//                mainFragAdapter.clearItem()
//
//            }
//        }
//    }


    private fun initList(query: String) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = Image_client.apiService.searchImage(
                    apiKey = Constants.AUTH_HEADER,
                    query = query,
                    sort = "recency",
                    page = 1,
                    size = 80
                )
                response.enqueue(object : retrofit2.Callback<Image> {
                    override fun onResponse(
                        call: retrofit2.Call<Image>,
                        response: Response<Image>
                    ) {
                        Log.d("initList()", "nyh ${response.body()?.meta?.totalCount}")
                        Log.d("initList()", "nyh ${response.isSuccessful}")
                        if (response.isSuccessful) {
                            val imageList = response.body()?.documents ?: emptyList()

//                            launch(Dispatchers.Main) {
                            Log.d("NetworkRequest", " nyh Images loaded: ${imageList.size}")
                            mainFragAdapter.setList(imageList)
                            mainFragAdapter.notifyDataSetChanged()
//                            }
                        } else {
                            Log.e("NetworkRequest", "nyh Failed to load images: ${response.code()}")
                            Toast.makeText(context, "불러오기실패", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<Image>, t: Throwable) {
                        Log.d("initList()", "nyh loaded?")
                    }
                })
            } catch (e: Exception) {

            }
        }
    }
}