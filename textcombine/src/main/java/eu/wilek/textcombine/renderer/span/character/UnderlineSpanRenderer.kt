package eu.wilek.textcombine.renderer.span.character

import android.text.style.UnderlineSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Underline
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class UnderlineSpanRenderer : SpanRenderer<Underline> {

    override fun renderSpan(styleSpan: Underline): Any {
        return UnderlineSpan()
    }
}
