package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.BackgroundColorSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import com.example.myapplication.ui.util.toColor

open class BackgroundColorSpanCreator : TextCombineSpan<BackgroundColor> {
    override fun createSpan(context: Context, styleSpan: BackgroundColor): Any {
        return BackgroundColorSpan(styleSpan.color.toColor(context = context))
    }
}