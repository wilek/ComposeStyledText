package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.BackgroundColorSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toColor

internal class BackgroundColorSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as BackgroundColor
        return BackgroundColorSpan(styleSpan.color.toColor(context = context))
    }
}
