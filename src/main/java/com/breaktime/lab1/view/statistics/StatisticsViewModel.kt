package com.breaktime.lab1.view.statistics

import androidx.lifecycle.ViewModel
import com.breaktime.lab1.data.ResultRepository

class StatisticsViewModel(repository: ResultRepository) : ViewModel() {
    val results = repository.readAllData
}