package com.example.myapplication.ui.resources

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

internal class StringResources(private val context: Context) {

    fun getString(@StringRes resId: Int) = context.getString(resId)

    fun getQuantityString(@PluralsRes resId: Int, quantity: Int) = context.resources.getQuantityString(resId, quantity)
}