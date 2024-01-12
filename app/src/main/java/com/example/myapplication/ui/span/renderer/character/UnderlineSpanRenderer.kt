package com.example.myapplication.ui.span.renderer.character

import android.text.style.UnderlineSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Underline

open class UnderlineSpanRenderer : SpanRenderer<Underline> {

    override fun renderSpan(styleSpan: Underline): Any {
        return UnderlineSpan()
    }
}
