package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.ForegroundColorSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toColor

open class ForegroundColorSpanRenderer(private val context: Context) : SpanRenderer<ForegroundColor> {

    override fun renderSpan(styleSpan: ForegroundColor): Any {
        return ForegroundColorSpan(styleSpan.color.toColor(context = context))
    }
}
