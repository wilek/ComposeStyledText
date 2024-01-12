package com.example.myapplication.ui.span.paragraph

import android.content.Context
import android.text.style.TabStopSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import com.example.myapplication.ui.util.toPx

open class TabStopSpanCreator : TextCombineSpanCreator<TabStop> {

    override fun createSpan(context: Context, styleSpan: TabStop): Any {
        return TabStopSpan.Standard(styleSpan.offset.toPx(context = context))
    }
}
