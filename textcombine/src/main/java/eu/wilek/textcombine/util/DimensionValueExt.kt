package eu.wilek.textcombine.util

import android.content.Context
import android.util.TypedValue.COMPLEX_UNIT_DIP
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.util.TypedValue.applyDimension
import eu.wilek.textcombine.TextCombine.DimensionValue

inline val Int.px: DimensionValue get() = DimensionValue.FromPx(value = this.toFloat())

inline val Double.px: DimensionValue get() = DimensionValue.FromPx(value = this.toFloat())

inline val Float.px: DimensionValue get() = DimensionValue.FromPx(value = this)

inline val Int.dp: DimensionValue get() = DimensionValue.FromDp(value = this.toFloat())

inline val Double.dp: DimensionValue get() = DimensionValue.FromDp(value = this.toFloat())

inline val Float.dp: DimensionValue get() = DimensionValue.FromDp(value = this)

inline val Int.sp: DimensionValue get() = DimensionValue.FromSp(value = this.toFloat())

inline val Double.sp: DimensionValue get() = DimensionValue.FromSp(value = this.toFloat())

inline val Float.sp: DimensionValue get() = DimensionValue.FromSp(value = this)

internal fun DimensionValue.toPx(context: Context) = when (this) {
    is DimensionValue.FromDp -> applyDimension(COMPLEX_UNIT_DIP, value, context.resources.displayMetrics)
    is DimensionValue.FromPx -> value
    is DimensionValue.FromSp -> applyDimension(COMPLEX_UNIT_SP, value, context.resources.displayMetrics)
    is DimensionValue.FromResource -> context.resources.getDimensionPixelOffset(dimenResId).toFloat()
}
