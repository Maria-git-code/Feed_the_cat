package com.breaktime.lab1.view.feed

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.breaktime.lab1.R
import com.breaktime.lab1.databinding.FragmentFeedBinding
import com.breaktime.lab1.widget.WidgetProvider
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FeedFragment : Fragment() {
    private lateinit var binding: FragmentFeedBinding
    private val viewModel: FeedViewModel by sharedViewModel()
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        username = arguments?.getString("username").toString()
        binding = FragmentFeedBinding.inflate(
            inflater, container, false
        ).apply {
            btnStat.setOnClickListener {
                findNavController().navigate(R.id.statisticsFragment)
            }
            btnShare.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, viewModel.satiety)
                startActivity(Intent.createChooser(intent, "share"))
            }
            tvSatiety.text = viewModel.satiety
            btnFeed.setOnClickListener {
                viewModel.increaseClicks()
                tvSatiety.text = viewModel.satiety
                if (viewModel.isAnimNeed()) {
                    imgCat.animate().apply {
                        duration = 200
                        translationYBy(-200f)
                    }.withEndAction {
                        imgCat.animate().apply {
                            duration = 200
                            translationYBy(200f)
                        }
                    }
                }
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.resetData()
    }

    override fun onStop() {
        super.onStop()
        viewModel.addResult(username)
        val appWidgetManager = AppWidgetManager.getInstance(context)
        val remoteViews = RemoteViews(requireContext().packageName, R.layout.widget)
        val thisWidget = ComponentName(requireContext(), WidgetProvider::class.java)
        remoteViews.setTextViewText(R.id.widget_text, viewModel.satiety)
        appWidgetManager.updateAppWidget(thisWidget, remoteViews)
    }
}