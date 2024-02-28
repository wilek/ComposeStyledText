package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.TextAppearanceSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.TextAppearance
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class TextAppearanceSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as TextAppearance
        return when (styleSpan.colorListResId) {
            null -> TextAppearanceSpan(context, styleSpan.appearanceResId)
            else -> TextAppearanceSpan(context, styleSpan.appearanceResId, styleSpan.colorListResId)
        }
    }
}
