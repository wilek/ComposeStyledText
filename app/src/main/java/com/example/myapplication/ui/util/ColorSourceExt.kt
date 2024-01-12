package com.example.myapplication.ui.util

import android.content.Context
import android.graphics.Color
import com.example.myapplication.ui.string.combine.TextCombine.ColorSource
import com.example.myapplication.ui.string.combine.TextCombine.ColorSource.FromInt
import com.example.myapplication.ui.string.combine.TextCombine.ColorSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.ColorSource.FromResources

internal fun ColorSource.toColor(context: Context) = when (this) {
    is FromString -> Color.parseColor(color)
    is FromInt -> color
    is FromResources -> context.getColor(resourceId)
}