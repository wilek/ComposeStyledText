package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.BackgroundColorSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toColor

open class BackgroundColorSpanRenderer(private val context: Context) : SpanRenderer<BackgroundColor> {
    override fun renderSpan(styleSpan: BackgroundColor): Any {
        return BackgroundColorSpan(styleSpan.color.toColor(context = context))
    }
}
