package eu.wilek.textcombine.renderer.span.character

import android.text.style.ScaleXSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ScaleX
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class ScaleXSpanRenderer : SpanRenderer<ScaleX> {

    override fun renderSpan(styleSpan: ScaleX): Any {
        return ScaleXSpan(styleSpan.proportion)
    }
}
