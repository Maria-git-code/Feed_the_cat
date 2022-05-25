package com.breaktime.lab1.di

import com.breaktime.lab1.data.ResultDatabase
import com.breaktime.lab1.data.ResultRepository
import com.breaktime.lab1.util.ResourcesProvider
import com.breaktime.lab1.view.achievements.AchievementsViewModel
import com.breaktime.lab1.view.feed.FeedViewModel

import com.breaktime.lab1.view.info.InfoViewModel
import com.breaktime.lab1.view.menu.MenuViewModel
import com.breaktime.lab1.view.splash.SplashViewModel
import com.breaktime.lab1.view.statistics.StatisticsViewModel
import com.breaktime.lab1.view.user_selection.UserSelectionViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ResourcesProvider(androidContext()) }
    single { ResultDatabase.getDatabase(androidApplication()).resultDao() }
    single { ResultRepository(get()) }

    viewModel { FeedViewModel(get(), get()) }
    viewModel { StatisticsViewModel(get()) }
    viewModel { MenuViewModel() }
    viewModel { SplashViewModel() }
    viewModel { UserSelectionViewModel(get()) }
    viewModel { InfoViewModel() }
    viewModel { AchievementsViewModel() }
}