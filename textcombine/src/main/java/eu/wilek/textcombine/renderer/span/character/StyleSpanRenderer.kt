package eu.wilek.textcombine.renderer.span.character

import android.graphics.Typeface
import android.text.style.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Style
import eu.wilek.textcombine.TextCombine.TypefaceStyle
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class StyleSpanRenderer : SpanRenderer<Style> {

    override fun renderSpan(styleSpan: Style): Any {
        return StyleSpan(styleSpan.typefaceStyle.toStyle())
    }

    private fun TypefaceStyle.toStyle() = when (this) {
        TypefaceStyle.NORMAL -> Typeface.NORMAL
        TypefaceStyle.BOLD -> Typeface.BOLD
        TypefaceStyle.ITALIC -> Typeface.ITALIC
        TypefaceStyle.BOLD_ITALIC -> Typeface.BOLD_ITALIC
    }
}
