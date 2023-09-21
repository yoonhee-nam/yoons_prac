package com.example.myapplication


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.myapplication.databinding.RecyclerItemBinding
import com.example.myapplication.model.Image


class MainFragAdapter(private val mcontext: Context) :
    RecyclerView.Adapter<MainFragAdapter.MyView>() {
    private var imageList = listOf<Image.Documents>()


//    fun clearItem() {
//        items.clear()
//        notifyDataSetChanged()
//    }

    inner class MyView(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var iv: ImageView = binding.iv
        var title: TextView = binding.tvTitle
        var datetime: TextView = binding.tvLowPrice

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        val currentItem = imageList[position]

        Glide.with(mcontext)
            .load(currentItem.thumbnailUrl)
            .into(holder.iv)

        holder.title.text = currentItem.displaySitename
        holder.datetime.text = currentItem.datetime

    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun setList(list: List<Image.Documents>) {
        imageList = list
    }
}