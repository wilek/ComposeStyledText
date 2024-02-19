package eu.wilek.textcombine.renderer.span.character

import android.text.style.StrikethroughSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Strikethrough
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class StrikethroughSpanRenderer : SpanRenderer<Strikethrough> {

    override fun renderSpan(styleSpan: Strikethrough): Any {
        return StrikethroughSpan()
    }
}
