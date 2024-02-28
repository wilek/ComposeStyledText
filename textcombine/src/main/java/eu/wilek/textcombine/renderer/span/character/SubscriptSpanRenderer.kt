package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.SubscriptSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class SubscriptSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        return SubscriptSpan()
    }
}
