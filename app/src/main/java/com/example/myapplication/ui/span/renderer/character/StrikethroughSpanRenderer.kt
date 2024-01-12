package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.StrikethroughSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Strikethrough

open class StrikethroughSpanRenderer : SpanRenderer<Strikethrough> {
    override fun renderSpan(context: Context, styleSpan: Strikethrough): Any {
        return StrikethroughSpan()
    }
}
