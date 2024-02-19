package eu.wilek.textcombine.renderer.span.character

import android.text.style.SuperscriptSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Superscript
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class SuperscriptSpanRenderer : SpanRenderer<Superscript> {

    override fun renderSpan(styleSpan: Superscript): Any {
        return SuperscriptSpan()
    }
}
