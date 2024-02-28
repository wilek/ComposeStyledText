package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.UnderlineSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class UnderlineSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        return UnderlineSpan()
    }
}
