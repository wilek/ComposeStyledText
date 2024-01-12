package com.example.myapplication.ui.util

import android.graphics.Typeface
import com.example.myapplication.ui.string.combine.TextCombine.TypefaceStyle

internal fun TypefaceStyle.toStyle() = when (this) {
    TypefaceStyle.NORMAL -> Typeface.NORMAL
    TypefaceStyle.BOLD -> Typeface.BOLD
    TypefaceStyle.ITALIC -> Typeface.ITALIC
    TypefaceStyle.BOLD_ITALIC -> Typeface.BOLD_ITALIC
}
