package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.ScaleXSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ScaleX
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class ScaleXSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as ScaleX
        return ScaleXSpan(styleSpan.proportion)
    }
}
