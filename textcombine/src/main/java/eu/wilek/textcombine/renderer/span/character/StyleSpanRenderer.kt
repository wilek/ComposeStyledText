package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.graphics.Typeface
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Style
import eu.wilek.textcombine.TextCombine.TypefaceStyle
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class StyleSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as Style
        return android.text.style.StyleSpan(styleSpan.typefaceStyle.toStyle())
    }

    private fun TypefaceStyle.toStyle() = when (this) {
        TypefaceStyle.NORMAL -> Typeface.NORMAL
        TypefaceStyle.BOLD -> Typeface.BOLD
        TypefaceStyle.ITALIC -> Typeface.ITALIC
        TypefaceStyle.BOLD_ITALIC -> Typeface.BOLD_ITALIC
    }
}
