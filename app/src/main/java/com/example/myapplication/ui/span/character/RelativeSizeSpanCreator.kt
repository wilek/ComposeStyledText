package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.RelativeSizeSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.RelativeSize

open class RelativeSizeSpanCreator : TextCombineSpanCreator<RelativeSize> {
    override fun createSpan(context: Context, styleSpan: RelativeSize): Any {
        return RelativeSizeSpan(styleSpan.proportion)
    }
}
