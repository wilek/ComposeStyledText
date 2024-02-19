package eu.wilek.textcombine.util

import android.content.Context
import android.graphics.Color
import eu.wilek.textcombine.TextCombine.ColorSource
import eu.wilek.textcombine.TextCombine.ColorSource.FromInt
import eu.wilek.textcombine.TextCombine.ColorSource.FromString
import eu.wilek.textcombine.TextCombine.ColorSource.FromResources

internal fun ColorSource.toColor(context: Context) = when (this) {
    is FromString -> Color.parseColor(color)
    is FromInt -> color
    is FromResources -> context.getColor(colorResId)
}
