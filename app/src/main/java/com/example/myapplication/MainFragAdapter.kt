package com.example.myapplication



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerItemBinding
import com.example.myapplication.model.Image


class MainFragAdapter : RecyclerView.Adapter<MainFragAdapter.MyView>() {
    private var imageList = listOf<Image.Documents>()

    inner class MyView(private val binding: RecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pos: Int) {
            binding.tvLowPrice.text = imageList[pos].imageUrl
            binding.tvMall.text = imageList[pos].collection
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyView(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.bind(position)
    }

    fun setList(list:List<Image.Documents>){
        imageList = list
    }
}
