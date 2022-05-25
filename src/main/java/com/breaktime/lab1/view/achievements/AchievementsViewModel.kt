package com.breaktime.lab1.view.achievements

import androidx.lifecycle.ViewModel

class AchievementsViewModel : ViewModel() {
    var list = mutableListOf(
        "Top 10" to 10,
        "Top 30" to 20,
        "Top 50" to 30
    )

    fun add(title: String, description: Int) {
        list.add(title to description)
    }
}