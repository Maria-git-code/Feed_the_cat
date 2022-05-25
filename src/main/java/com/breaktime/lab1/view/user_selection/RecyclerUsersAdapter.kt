package com.breaktime.lab1.view.user_selection

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.breaktime.lab1.databinding.UserItemBinding

class RecyclerUsersAdapter(private val onItemClick: (View) -> Unit) :
    RecyclerView.Adapter<RecyclerUsersAdapter.ViewHolder>() {
    var items = emptyList<String>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UserItemBinding = UserItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String) = with(binding) {
            username.text = name
            root.setOnClickListener(onItemClick)
        }
    }
}
