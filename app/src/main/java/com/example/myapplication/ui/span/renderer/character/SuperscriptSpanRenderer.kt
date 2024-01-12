package com.example.myapplication.ui.span.renderer.character

import android.text.style.SuperscriptSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Superscript

open class SuperscriptSpanRenderer : SpanRenderer<Superscript> {

    override fun renderSpan(styleSpan: Superscript): Any {
        return SuperscriptSpan()
    }
}
