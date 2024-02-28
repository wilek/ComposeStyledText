package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.StrikethroughSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class StrikethroughSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        return StrikethroughSpan()
    }
}
