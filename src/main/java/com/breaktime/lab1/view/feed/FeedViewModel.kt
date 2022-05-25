package com.breaktime.lab1.view.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breaktime.lab1.R
import com.breaktime.lab1.data.ResultEntity
import com.breaktime.lab1.data.ResultRepository
import com.breaktime.lab1.util.ResourcesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FeedViewModel(
    private val resProvider: ResourcesProvider,
    private val repository: ResultRepository
) : ViewModel() {
    private var clicks = 0
    private var date: Date = Calendar.getInstance().time

    val satiety: String
        get() {
            return resProvider.getString(R.string.satiety, clicks)
        }

    fun increaseClicks() {
        clicks++
    }

    fun isAnimNeed() = clicks % 15 == 0

    fun resetData() {
        clicks = 0
        date = Calendar.getInstance().time
    }

    fun addResult(username: String) {
        if (clicks != 0)
            viewModelScope.launch(Dispatchers.IO) {
                repository.addResult(ResultEntity(date, clicks, username))
            }
    }
}