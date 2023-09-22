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

    private var binding: FragmentLikeBinding? = null
    private lateinit var Context1 : Context
    private var likedImage: List<SearchData> = listOf()
    private lateinit var adapter: LikeFragAdapter


    private var param1: String? = null

    override fun onAttach(context: Context){
        super.onAttach(context)
        Context1 = context
    }

    override fun onResume() {
        super.onResume()
        val main = activity as MainActivity
        likedImage = main.likeimage
        adapter.image = likedImage.toMutableList()
        adapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        _binding = FragmentLikeBinding.inflate(inflater, container, false)

        adapter = LikeFragAdapter(Context1).apply {
            image = likedImage.toMutableList()
        }
            binding = FragmentLikeBinding.inflate(inflater, container, false).apply {
                likeRecycler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
        return binding?.root
        }


    }





//    companion object {
//
//        fun newInstance(param1: String) =
//            LikeFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1,param1)
//                }
//            }
//    }
