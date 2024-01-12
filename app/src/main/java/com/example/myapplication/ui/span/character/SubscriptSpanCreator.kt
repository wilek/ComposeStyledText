package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.SubscriptSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Subscript

open class SubscriptSpanCreator : TextCombineSpanCreator<Subscript> {
    override fun createSpan(context: Context, styleSpan: Subscript): Any {
        return SubscriptSpan()
    }
}
