package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.RelativeSizeSpan
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.RelativeSize

open class RelativeSizeSpanCreator : TextCombineSpan<RelativeSize> {
    override fun createSpan(context: Context, styleSpan: RelativeSize): Any {
        return RelativeSizeSpan(styleSpan.proportion)
    }
}
