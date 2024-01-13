package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.ForegroundColorSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import com.example.myapplication.ui.util.toColor

open class ForegroundColorSpanRenderer(private val context: Context) : SpanRenderer<ForegroundColor> {

    override fun renderSpan(styleSpan: ForegroundColor): Any {
        return ForegroundColorSpan(styleSpan.color.toColor(context = context))
    }
}