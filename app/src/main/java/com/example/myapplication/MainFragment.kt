package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.api.Image_client
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.model.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


@Suppress("UNREACHABLE_CODE")
class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var mainFragAdapter: MainFragAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        var imageList = listOf<Image.Documents>()

        binding.button.setOnClickListener {
            mainFragAdapter.setList(imageList)
            mainFragAdapter.notifyDataSetChanged()
        }

        binding.recycler.apply {
            adapter = mainFragAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        initList()
        return binding.root
    }

    private fun initList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = Image_client.apiService.searchImage(
                    query = "",
                    sort = "",
                    page = 1,
                    apiKey = Constants.AUTH_HEADER,
                    size = 5
                )
                if (response.isSuccessful) {
                    val reponseBody = response.body()
                    val imageList = response.body()?.documents ?: emptyList()
                    withContext(Dispatchers.Main) {
                        mainFragAdapter.setList(imageList)
                        mainFragAdapter.notifyDataSetChanged()
                    }
                } else {
                    Toast.makeText(context, "불러오기 실패", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {

            }
        }
    }
}