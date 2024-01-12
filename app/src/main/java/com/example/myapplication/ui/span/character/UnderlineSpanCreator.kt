package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.UnderlineSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Underline

open class UnderlineSpanCreator : TextCombineSpanCreator<Underline> {
    override fun createSpan(context: Context, styleSpan: Underline): Any {
        return UnderlineSpan()
    }
}
