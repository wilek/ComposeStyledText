package com.example.myapplication.ui.span.renderer.character

import android.content.Context
import android.text.style.ScaleXSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ScaleX

open class ScaleXSpanRenderer : SpanRenderer<ScaleX> {
    override fun renderSpan(context: Context, styleSpan: ScaleX): Any {
        return ScaleXSpan(styleSpan.proportion)
    }
}
