package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.SuperscriptSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Superscript

open class SuperscriptSpanCreator : TextCombineSpanCreator<Superscript> {
    override fun createSpan(context: Context, styleSpan: Superscript): Any {
        return SuperscriptSpan()
    }
}
