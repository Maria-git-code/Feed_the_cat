package com.breaktime.lab1.view.splash

import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {
    private var _progressCounter = 0
    val progressCounter: Int
        get() = _progressCounter
    val maxProgress = 100

    fun increaseCounter() {
        _progressCounter++
        if (_progressCounter > maxProgress) throw Exception("progressCounter more then max progress")
    }

    fun endProgress() = _progressCounter == maxProgress
}