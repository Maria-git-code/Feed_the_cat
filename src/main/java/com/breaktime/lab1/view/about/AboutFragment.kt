package com.breaktime.lab1.view.about

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.breaktime.lab1.R
import com.breaktime.lab1.databinding.FragmentAboutBinding
import com.breaktime.lab1.databinding.FragmentMenuBinding
import com.breaktime.lab1.view.menu.MenuViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AboutFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAboutBinding.inflate(
            inflater, container, false
        ).root
    }
}