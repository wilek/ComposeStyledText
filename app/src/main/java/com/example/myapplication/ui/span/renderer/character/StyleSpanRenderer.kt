package com.example.myapplication.ui.span.renderer.character

import android.graphics.Typeface
import android.text.style.StyleSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Style
import com.example.myapplication.ui.string.combine.TextCombine.TypefaceStyle

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
