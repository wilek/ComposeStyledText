package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.StyleSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Style
import com.example.myapplication.ui.util.toStyle

open class StyleSpanCreator : TextCombineSpan<Style> {
    override fun createSpan(context: Context, styleSpan: Style): Any {
        return StyleSpan(styleSpan.typefaceStyle.toStyle())
    }
}
