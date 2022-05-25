package com.breaktime.lab1.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import com.breaktime.lab1.R
import com.breaktime.lab1.data.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class WidgetProvider : AppWidgetProvider(), KoinComponent {
    private val repository: ResultRepository by inject()
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        appWidgetIds?.forEach {
            val view = RemoteViews(context?.packageName, R.layout.widget)
            GlobalScope.launch(Dispatchers.IO) {
                view.setTextViewText(
                    R.id.widget_text,
                    "Satiety ${repository.getLastScore().satiety}"
                )
                withContext(Dispatchers.Main) {
                    appWidgetManager?.updateAppWidget(it, view)
                }
            }
        }
    }
}