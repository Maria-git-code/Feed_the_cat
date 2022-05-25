package com.breaktime.lab1.view.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.breaktime.lab1.databinding.SliderItemBinding
import com.breaktime.lab1.util.ResourcesProvider


class SliderAdapter(private val resProvider: ResourcesProvider, private var items: List<Pair<Int, Int>>) :
    RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SliderItemBinding = SliderItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (img, description) = items[position]
        holder.bind(img, description)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(private val binding: SliderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(img: Int, description: Int) = with(binding) {
            imageView.setImageDrawable(resProvider.getDrawable(img))
            text.text = resProvider.getString(description)
        }
    }
}