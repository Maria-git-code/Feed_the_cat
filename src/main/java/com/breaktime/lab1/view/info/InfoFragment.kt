package com.breaktime.lab1.view.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.breaktime.lab1.databinding.FragmentInfoBinding
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private val viewModel: InfoViewModel by sharedViewModel()
    private lateinit var dots: Array<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dots = Array(size = viewModel.data.size) {
            val view = TextView(requireContext())
            view.text = "◦"
            view.textSize = 28F
            view
        }
        binding = FragmentInfoBinding.inflate(
            inflater, container, false
        ).apply {
            val adapter = SliderAdapter(get(), viewModel.data)
            viewPager.adapter = adapter
            next.setOnClickListener {
                println(viewPager.currentItem + 1)
                println(viewModel.data.size)
                if (viewPager.currentItem + 1 >= viewModel.data.size) {
                    findNavController().popBackStack()
                } else {
                    viewPager.setCurrentItem(viewPager.currentItem + 1, true)
                }
            }
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    selectedDots(position)
                }
            })
        }
        setIndicators()
        return binding.root
    }

    private fun selectedDots(position: Int) {
        for (i in dots.indices) {
            if (i == position) {
                dots[i].text = "•"
            } else {
                dots[i].text = "◦"
            }
        }
    }

    private fun setIndicators() {
        for (i in dots.indices) {
            binding.dots.addView(dots[i])
        }
    }
}