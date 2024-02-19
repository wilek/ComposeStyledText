package eu.wilek.textcombine.renderer.span.character

import android.text.style.RelativeSizeSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.RelativeSize
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class RelativeSizeSpanRenderer : SpanRenderer<RelativeSize> {
    override fun renderSpan(styleSpan: RelativeSize): Any {
        return RelativeSizeSpan(styleSpan.proportion)
    }
}
