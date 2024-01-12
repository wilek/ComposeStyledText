package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.AbsoluteSizeSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import com.example.myapplication.ui.util.toPx

open class AbsoluteSizeSpanCreator : TextCombineSpanCreator<AbsoluteSize> {
    override fun createSpan(context: Context, styleSpan: AbsoluteSize): Any {
        return AbsoluteSizeSpan(styleSpan.size.toPx(context = context))
    }
}
