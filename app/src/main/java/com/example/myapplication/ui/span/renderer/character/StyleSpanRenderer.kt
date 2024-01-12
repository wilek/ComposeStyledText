package com.example.myapplication.ui.span.renderer.character

import android.text.style.StyleSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Style
import com.example.myapplication.ui.util.toStyle

open class StyleSpanRenderer : SpanRenderer<Style> {

    override fun renderSpan(styleSpan: Style): Any {
        return StyleSpan(styleSpan.typefaceStyle.toStyle())
    }
}
