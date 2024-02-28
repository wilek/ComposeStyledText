package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.RelativeSizeSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.RelativeSize
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class RelativeSizeSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as RelativeSize
        return RelativeSizeSpan(styleSpan.proportion)
    }
}
