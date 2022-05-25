package com.breaktime.lab1.view.statistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.breaktime.lab1.databinding.FragmentStatisticsBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class StatisticsFragment : Fragment() {
    private lateinit var binding: FragmentStatisticsBinding
    private val viewModel: StatisticsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStatisticsBinding.inflate(
            inflater, container, false
        ).apply {
            val adapter = RecyclerResultsAdapter(get())
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = adapter
            viewModel.results.onEach {
                if (it.isEmpty()) {
                    switchRecycler(false)
                    return@onEach
                }
                switchRecycler(true)
                adapter.items = it
            }.launchIn(lifecycleScope)
        }
        return binding.root
    }

    private fun switchRecycler(isVisible: Boolean) = with(binding) {
        loading.visibility = View.GONE
        if (isVisible) {
            warning.visibility = View.GONE
            list.visibility = View.VISIBLE
        } else {
            warning.visibility = View.VISIBLE
            list.visibility = View.GONE
        }
    }
}