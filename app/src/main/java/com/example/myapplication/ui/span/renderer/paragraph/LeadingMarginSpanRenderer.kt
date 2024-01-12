package com.example.myapplication.ui.span.renderer.paragraph

import android.content.Context
import android.text.style.LeadingMarginSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin

open class LeadingMarginSpanRenderer : SpanRenderer<LeadingMargin> {

    override fun renderSpan(context: Context, styleSpan: LeadingMargin): Any {
        return LeadingMarginSpan.Standard(styleSpan.first, styleSpan.rest)
    }
}
