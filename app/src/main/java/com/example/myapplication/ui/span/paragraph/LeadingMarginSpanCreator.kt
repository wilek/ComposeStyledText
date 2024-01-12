package com.example.myapplication.ui.span.paragraph

import android.content.Context
import android.text.style.LeadingMarginSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin

open class LeadingMarginSpanCreator : TextCombineSpan<LeadingMargin> {

    override fun createSpan(context: Context, styleSpan: LeadingMargin): Any {
        return LeadingMarginSpan.Standard(styleSpan.first, styleSpan.rest)
    }
}
