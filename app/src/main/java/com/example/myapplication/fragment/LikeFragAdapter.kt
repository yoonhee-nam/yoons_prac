package com.example.myapplication.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.databinding.RecyclerItemBinding
import com.example.myapplication.model.Image
import com.example.myapplication.model.SearchData

class LikeFragAdapter(var Context2: Context) :
    RecyclerView.Adapter<LikeFragAdapter.LikeViewHolder>() {

    var image = mutableListOf<SearchData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikeViewHolder {
        val binding = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return LikeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return image.size
    }


    override fun onBindViewHolder(holder: LikeFragAdapter.LikeViewHolder, position: Int) {

        val currentItem = image[position]

        Glide.with(Context2)
            .load(image[position].url)
            .into((holder as LikeViewHolder).search_image)

        holder.title.text = currentItem.title
        holder.like_image.visibility = View.GONE
        holder.dateTime.text = currentItem.dateTime
    }

    inner class LikeViewHolder(binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var search_image: ImageView = binding.iv
        var like_image: ImageView = binding.like
        var title: TextView = binding.tvTitle
        var dateTime: TextView = binding.tvLowPrice
        var img_const: ConstraintLayout = binding.searchImgConstraint

        init {
            like_image.visibility = View.GONE
            img_const.setOnClickListener{
                val position = adapterPosition
                (Context2 as MainActivity).removeLikeImage(image[position])
                if(position != RecyclerView.NO_POSITION){
                    image.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }
}


