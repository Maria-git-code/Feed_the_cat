package com.breaktime.lab1.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources

class ResourcesProvider(
    private val context: Context
) {
    fun getDrawable(drawableResId: Int): Drawable? {
        return AppCompatResources.getDrawable(context, drawableResId)
    }

    fun getString(stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun getString(stringResId: Int, vararg params: Any): String {
        return context.getString(stringResId, *params)
    }
}