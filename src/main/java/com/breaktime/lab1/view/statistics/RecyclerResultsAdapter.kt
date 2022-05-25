package com.breaktime.lab1.view.statistics

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.breaktime.lab1.R
import com.breaktime.lab1.data.ResultEntity
import com.breaktime.lab1.databinding.ResultItemBinding
import com.breaktime.lab1.util.ResourcesProvider
import java.text.SimpleDateFormat
import java.util.*

class RecyclerResultsAdapter(val resProvider: ResourcesProvider) :
    RecyclerView.Adapter<RecyclerResultsAdapter.ViewHolder>() {
    var items = emptyList<ResultEntity>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ResultItemBinding = ResultItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: ResultEntity) = with(binding) {
            val formatter = SimpleDateFormat("dd.MM.yyyy hh:mm", Locale.getDefault())
            val time = formatter.format(result.time)
            username.text = result.username
            date.text = time
            score.text = resProvider.getString(
                R.string.satiety, result.satiety
            )
        }
    }
}
