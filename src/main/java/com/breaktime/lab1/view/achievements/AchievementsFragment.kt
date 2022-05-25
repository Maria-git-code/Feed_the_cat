package com.breaktime.lab1.view.achievements

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.breaktime.lab1.R
import com.breaktime.lab1.databinding.FragmentAchivementsBinding
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AchievementsFragment : Fragment() {
    private lateinit var binding: FragmentAchivementsBinding
    private val viewModel: AchievementsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAchivementsBinding.inflate(
            inflater, container, false
        ).apply {
            val adapter = RecyclerAchievementsAdapter(get(), get())
            adapter.items = viewModel.list
            list.layoutManager = LinearLayoutManager(context)
            list.adapter = adapter

            add.setOnClickListener {
                dialog()?.show()
            }
        }
        return binding.root
    }

    private fun dialog(): AlertDialog? {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(getString(R.string.dialog_achievement_title))
        val layout = LinearLayout(requireContext())
        layout.orientation = LinearLayout.VERTICAL
        val name = EditText(requireContext())
        name.inputType = InputType.TYPE_CLASS_TEXT
        name.hint = getString(R.string.dialog_achievement_name_hint)
        layout.addView(name)
        val number = EditText(requireContext())
        number.hint = getString(R.string.dialog_achievement_score_hint)
        number.inputType = InputType.TYPE_CLASS_NUMBER
        layout.addView(number)
        builder.setView(layout)

        builder.setPositiveButton(
            getString(R.string.dialog_achievement_add)
        ) { dialog, _ ->
            if (name.text.isNotEmpty() && number.text.isNotEmpty()) {
                viewModel.add(name.text.toString(), number.text.toString().toInt())
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.dialog_achievement_invalid_fields),
                    Toast.LENGTH_SHORT
                ).show()
            }
            dialog.dismiss()

        }
        builder.setNegativeButton(
            getString(R.string.dialog_achievement_cancel)
        ) { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }
}