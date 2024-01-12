package com.example.myapplication.ui.span.renderer.character

import android.text.style.RelativeSizeSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.RelativeSize

open class RelativeSizeSpanRenderer : SpanRenderer<RelativeSize> {
    override fun renderSpan(styleSpan: RelativeSize): Any {
        return RelativeSizeSpan(styleSpan.proportion)
    }
}
