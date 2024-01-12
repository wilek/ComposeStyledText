package com.example.myapplication.ui.span.renderer.paragraph

import android.content.Context
import android.text.style.TabStopSpan
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import com.example.myapplication.ui.util.toPx

open class TabStopSpanRenderer : SpanRenderer<TabStop> {

    override fun renderSpan(context: Context, styleSpan: TabStop): Any {
        return TabStopSpan.Standard(styleSpan.offset.toPx(context = context))
    }
}
