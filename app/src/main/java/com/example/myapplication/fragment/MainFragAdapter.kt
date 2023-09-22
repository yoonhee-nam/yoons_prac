package com.example.myapplication.fragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.MainActivity
import com.example.myapplication.databinding.RecyclerItemBinding
import com.example.myapplication.model.Image
import com.example.myapplication.model.SearchData

class MainFragAdapter(private val mcontext: Context) :
    RecyclerView.Adapter<MainFragAdapter.MyView>() {
     var imageList = listOf<SearchData>()


//    fun clearItem() {
//        items.clear()
//        notifyDataSetChanged()
//    }

    inner class MyView(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        var iv: ImageView = binding.iv
        var title: TextView = binding.tvTitle
        var like_image: ImageView = binding.like
        var datetime: TextView = binding.tvLowPrice
        var img_const: ConstraintLayout = binding.searchImgConstraint

        init {
            like_image.visibility = View.GONE
            iv.setOnClickListener(this)
            img_const.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            Log.d("MainAdapter","nyh Click?")
            val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION } ?: return
            val image = imageList[position]

            image.like = !image.like

            if (image.like) {

                (mcontext as MainActivity).addLikedImage(image)
            } else {
                Log.d("Onclick","nyh nopass??")
                (mcontext as MainActivity).removeLikeImage(image)
            }
            notifyItemChanged(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {

        val currentItem = imageList[position]

        Glide.with(mcontext)
            .load(currentItem.url)
            .into(holder.iv)

        holder.like_image.visibility =
            if (imageList[position].like) {
                View.VISIBLE
            }
            else View.INVISIBLE
        holder.title.text = currentItem.title
        holder.datetime.text = currentItem.dateTime
    }


    override fun getItemCount(): Int {
        return imageList.size
    }

    fun setList(list: List<SearchData>) {
        imageList = list
    }
}