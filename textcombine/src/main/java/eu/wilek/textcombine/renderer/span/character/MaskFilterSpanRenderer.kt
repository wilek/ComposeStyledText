package eu.wilek.textcombine.renderer.span.character

import android.graphics.BlurMaskFilter
import android.text.style.MaskFilterSpan
import eu.wilek.textcombine.TextCombine.BlurType
import eu.wilek.textcombine.TextCombine.MaskFilterType
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.MaskFilter
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class MaskFilterSpanRenderer : SpanRenderer<MaskFilter> {
    override fun renderSpan(styleSpan: MaskFilter): Any {
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
