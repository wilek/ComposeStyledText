package eu.wilek.textcombine.util

import android.content.Context
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.util.TypedValue.applyDimension
import eu.wilek.textcombine.TextCombine.DimensionValue
import kotlin.math.roundToInt

internal fun DimensionValue.toPx(context: Context) = when (this) {
    is DimensionValue.FromDp -> applyDimension(COMPLEX_UNIT_DIP, value.toFloat(), context.resources.displayMetrics).roundToInt()
    is DimensionValue.FromPx -> value
    is DimensionValue.FromSp -> applyDimension(COMPLEX_UNIT_SP, value.toFloat(), context.resources.displayMetrics).roundToInt()
    is DimensionValue.FromResource -> context.resources.getDimensionPixelOffset(dimenResId)
}