package com.example.myapplication.ui.span.paragraph

import android.content.Context
import android.text.style.DrawableMarginSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import com.example.myapplication.ui.util.toImage

open class LeadingImageSpanCreator : TextCombineSpanCreator<LeadingImage> {

    override fun createSpan(context: Context, styleSpan: LeadingImage): Any {
        return DrawableMarginSpan(styleSpan.image.toImage(context = context, size = styleSpan.size, margin = styleSpan.margin))
    }
}
