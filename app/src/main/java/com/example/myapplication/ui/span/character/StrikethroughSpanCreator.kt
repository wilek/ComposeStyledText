package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.StrikethroughSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Strikethrough

open class StrikethroughSpanCreator : TextCombineSpan<Strikethrough> {
    override fun createSpan(context: Context, styleSpan: Strikethrough): Any {
        return StrikethroughSpan()
    }
}
