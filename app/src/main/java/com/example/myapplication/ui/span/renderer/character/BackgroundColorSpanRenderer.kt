package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.BackgroundColorSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import com.example.myapplication.ui.util.toColor

open class BackgroundColorSpanRenderer(private val context: Context) : SpanRenderer<BackgroundColor> {
    override fun renderSpan(styleSpan: BackgroundColor): Any {
        return BackgroundColorSpan(styleSpan.color.toColor(context = context))
    }
}