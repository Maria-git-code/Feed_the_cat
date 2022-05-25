package com.breaktime.lab1.view.user_selection

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.breaktime.lab1.R
import com.breaktime.lab1.databinding.FragmentUserSelectionBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserSelectionFragment : Fragment() {
    private lateinit var binding: FragmentUserSelectionBinding
    private val viewModel: UserSelectionViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserSelectionBinding.inflate(
            inflater, container, false
        ).apply {
            val adapter = RecyclerUsersAdapter {
                val layout = it as LinearLayout
                val username = layout.findViewById<TextView>(R.id.username).text
                findNavController().navigate(R.id.feedFragment, bundleOf("username" to username))
            }
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

            create.setOnClickListener {
                val username = name.text.toString()
                findNavController().navigate(R.id.feedFragment, bundleOf("username" to username))
            }
        }
        return binding.root
    }

    private fun switchRecycler(isVisible: Boolean) = with(binding) {
        if (isVisible) {
            warning.visibility = View.GONE
            list.visibility = View.VISIBLE
        } else {
            warning.visibility = View.VISIBLE
            list.visibility = View.GONE
        }
    }
}