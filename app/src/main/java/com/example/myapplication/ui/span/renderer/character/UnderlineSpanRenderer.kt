package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.UnderlineSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Underline

open class UnderlineSpanRenderer : SpanRenderer<Underline> {
    override fun renderSpan(context: Context, styleSpan: Underline): Any {
        return UnderlineSpan()
    }
}
