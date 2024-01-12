package com.example.myapplication.ui.util

import android.content.Context
import com.example.myapplication.ui.string.combine.TextCombine.DimensionValue
import kotlin.math.roundToInt

internal fun DimensionValue.toPx(context: Context) = when (this) {
    is DimensionValue.FromDp -> (value * context.resources.displayMetrics.density).roundToInt()
    is DimensionValue.FromPx -> value
    is DimensionValue.FromSp -> (value * context.resources.displayMetrics.scaledDensity).roundToInt()
    is DimensionValue.FromResource -> context.resources.getDimensionPixelOffset(dimenResId)
}