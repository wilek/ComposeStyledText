package eu.wilek.textcombine.renderer.span.character

import android.text.style.SubscriptSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Subscript
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class SubscriptSpanRenderer : SpanRenderer<Subscript> {

    override fun renderSpan(styleSpan: Subscript): Any {
        return SubscriptSpan()
    }
}
