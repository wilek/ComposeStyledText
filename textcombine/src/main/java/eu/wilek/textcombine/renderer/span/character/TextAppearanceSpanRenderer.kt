package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.TextAppearanceSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.TextAppearance
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class TextAppearanceSpanRenderer(private val context: Context) : SpanRenderer<TextAppearance> {

    override fun renderSpan(styleSpan: TextAppearance): Any {
        return TextAppearanceSpan(context, styleSpan.appearanceResId, styleSpan.colorListResId)
    }
}
