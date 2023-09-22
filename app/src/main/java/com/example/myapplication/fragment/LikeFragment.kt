package com.example.myapplication.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentLikeBinding
import com.example.myapplication.model.Image
import com.example.myapplication.model.SearchData

private const val ARG_PARAM1 = "param1"

class LikeFragment : Fragment() {

    private lateinit var Context1 : Context
    private var binding: FragmentLikeBinding? = null
    private lateinit var adapter: LikeFragAdapter

    private var likedImage: List<SearchData> = listOf()

    override fun onAttach(context: Context){
        super.onAttach(context)
        Context1 = context
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        val main = activity as MainActivity
        likedImage = main.likeItems
        adapter.image.clear()
        adapter.image.addAll(likedImage)
        Log.d("LikeFrag","nyh ${likedImage.size}.!@")
        adapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val mainActivity = activity as MainActivity
//
//        likedImage = mainActivity.likeItems

        adapter = LikeFragAdapter(Context1).apply {
            image = likedImage.toMutableList()
        }
        binding = FragmentLikeBinding.inflate(inflater, container, false).apply {
            likeRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
        binding?.likeRecycler?.adapter = adapter
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}