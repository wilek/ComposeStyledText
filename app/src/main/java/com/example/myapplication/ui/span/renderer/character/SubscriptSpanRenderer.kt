package com.example.myapplication.ui.span.renderer.character

import android.text.style.SubscriptSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Subscript

open class SubscriptSpanRenderer : SpanRenderer<Subscript> {

    override fun renderSpan(styleSpan: Subscript): Any {
        return SubscriptSpan()
    }
}