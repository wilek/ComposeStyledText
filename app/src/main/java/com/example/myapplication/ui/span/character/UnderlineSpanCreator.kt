package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.UnderlineSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Underline

open class UnderlineSpanCreator : TextCombineSpan<Underline> {
    override fun createSpan(context: Context, styleSpan: Underline): Any {
        return UnderlineSpan()
    }
}
