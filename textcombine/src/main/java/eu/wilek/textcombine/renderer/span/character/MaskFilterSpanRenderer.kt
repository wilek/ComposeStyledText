package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.graphics.BlurMaskFilter
import android.text.style.MaskFilterSpan
import eu.wilek.textcombine.TextCombine.BlurType
import eu.wilek.textcombine.TextCombine.MaskFilterType
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.MaskFilter
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class MaskFilterSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as MaskFilter
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
