package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.TextAppearanceSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.TextAppearance

open class TextAppearanceSpanRenderer(private val context: Context) : SpanRenderer<TextAppearance> {

    override fun renderSpan(styleSpan: TextAppearance): Any {
        return TextAppearanceSpan(context, styleSpan.appearanceResId, styleSpan.colorListResId)
    }
}
