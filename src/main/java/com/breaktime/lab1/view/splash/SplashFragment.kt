package com.breaktime.lab1.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.breaktime.lab1.R
import com.breaktime.lab1.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by sharedViewModel()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(
            inflater, container, false
        ).apply {
            val animation = AnimationUtils.loadAnimation(context, R.anim.fade_animation)
            animation.repeatCount = Animation.INFINITE
            imgCat.startAnimation(animation)
            progressBar.max = viewModel.maxProgress
            Timer().also {
                it.schedule(object : TimerTask() {
                    override fun run() {
                        viewModel.increaseCounter()
                        progressBar.progress = viewModel.progressCounter
                        if (viewModel.endProgress()) {
                            it.cancel()
                            handler.post { findNavController().navigate(R.id.menuFragment) }
                        }
                    }
                }, 0, 50)
            }
        }
        return binding.root
    }
}