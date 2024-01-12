package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.ScaleXSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.ScaleX

open class ScaleXSpanCreator : TextCombineSpanCreator<ScaleX> {
    override fun createSpan(context: Context, styleSpan: ScaleX): Any {
        return ScaleXSpan(styleSpan.proportion)
    }
}
