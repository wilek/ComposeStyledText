package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.AbsoluteSizeSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import com.example.myapplication.ui.util.toPx

open class AbsoluteSizeSpanRenderer(private val context: Context) : SpanRenderer<AbsoluteSize> {
    override fun renderSpan(styleSpan: AbsoluteSize): Any {
        return AbsoluteSizeSpan(styleSpan.size.toPx(context = context))
    }
}
