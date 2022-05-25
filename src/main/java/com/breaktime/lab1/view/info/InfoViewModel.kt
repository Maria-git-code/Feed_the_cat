package com.breaktime.lab1.view.info

import androidx.lifecycle.ViewModel
import com.breaktime.lab1.R

class InfoViewModel : ViewModel() {
    val data = listOf(
        R.drawable.screen_splash to R.string.screen_description_splash,
        R.drawable.screen_main to R.string.screen_description_menu,
        R.drawable.screen_achievements to R.string.screen_description_achievements,
        R.drawable.screen_dialog to R.string.screen_description_add_achievement,
        R.drawable.screen_select_user to R.string.screen_description_add_user,
        R.drawable.screen_feed to R.string.screen_description_feed,
        R.drawable.screen_share to R.string.screen_description_share,
        R.drawable.screen_scores to R.string.screen_description_scores,


    )
}