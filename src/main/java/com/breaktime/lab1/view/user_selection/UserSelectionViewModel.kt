package com.breaktime.lab1.view.user_selection

import androidx.lifecycle.ViewModel
import com.breaktime.lab1.data.ResultRepository

class UserSelectionViewModel(repository: ResultRepository) : ViewModel() {
    val results = repository.readAllNames

}