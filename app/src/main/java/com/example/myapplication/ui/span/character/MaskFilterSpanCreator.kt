package com.example.myapplication.ui.span.character

import android.content.Context
import android.graphics.BlurMaskFilter
import android.text.style.MaskFilterSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.BlurType
import com.example.myapplication.ui.string.combine.TextCombine.MaskFilterType
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.MaskFilter

open class MaskFilterSpanCreator : TextCombineSpan<MaskFilter> {
    override fun createSpan(context: Context, styleSpan: MaskFilter): Any {
        return MaskFilterSpan(styleSpan.filterType.toMaskFilter())
    }

    private fun MaskFilterType.toMaskFilter() = when (this) {
        is MaskFilterType.Blur -> BlurMaskFilter(radius, blurType.toBlurStyle())
    }

    private fun BlurType.toBlurStyle() = when (this) {
        BlurType.NORMAL -> BlurMaskFilter.Blur.NORMAL
        BlurType.SOLID -> BlurMaskFilter.Blur.SOLID
        BlurType.OUTER -> BlurMaskFilter.Blur.OUTER
        BlurType.INNER -> BlurMaskFilter.Blur.INNER
    }
}
