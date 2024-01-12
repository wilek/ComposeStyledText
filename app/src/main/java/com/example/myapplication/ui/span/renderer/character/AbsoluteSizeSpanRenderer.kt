package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.AbsoluteSizeSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import com.example.myapplication.ui.util.toPx

open class AbsoluteSizeSpanRenderer : SpanRenderer<AbsoluteSize> {
    override fun renderSpan(context: Context, styleSpan: AbsoluteSize): Any {
        return AbsoluteSizeSpan(styleSpan.size.toPx(context = context))
    }
}
