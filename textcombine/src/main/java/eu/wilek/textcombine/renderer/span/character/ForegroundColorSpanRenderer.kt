package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.ForegroundColorSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toColor

internal class ForegroundColorSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as ForegroundColor
        return ForegroundColorSpan(styleSpan.color.toColor(context = context))
    }
}
