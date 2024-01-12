package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.ForegroundColorSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
import com.example.myapplication.ui.util.toColor

open class ForegroundColorSpanCreator : TextCombineSpan<ForegroundColor> {
    override fun createSpan(context: Context, styleSpan: ForegroundColor): Any {
        return ForegroundColorSpan(styleSpan.color.toColor(context = context))
    }
}
