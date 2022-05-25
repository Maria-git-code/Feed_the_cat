package com.breaktime.lab1.view.achievements

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.breaktime.lab1.R
import com.breaktime.lab1.data.ResultRepository
import com.breaktime.lab1.databinding.AchievementItemBinding
import com.breaktime.lab1.databinding.FragmentAchivementsBinding
import com.breaktime.lab1.databinding.UserItemBinding
import com.breaktime.lab1.util.ResourcesProvider
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.lastOrNull

class RecyclerAchievementsAdapter(
    private val resProvider: ResourcesProvider,
    private val repository: ResultRepository
) :
    RecyclerView.Adapter<RecyclerAchievementsAdapter.ViewHolder>() {
    var items = emptyList<Pair<String, Int>>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AchievementItemBinding = AchievementItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: AchievementItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pair: Pair<String, Int>) = with(binding) {
            val (name, completed) = pair
            binding.title.text = name
            binding.description.text = resProvider.getString(
                R.string.achievement_part,
                completed,
                repository.maxSatiety.satiety
            )
        }
    }
}
