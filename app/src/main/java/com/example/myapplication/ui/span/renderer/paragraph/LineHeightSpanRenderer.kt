package com.example.myapplication.ui.span.renderer.paragraph

import android.content.Context
import android.graphics.Paint
import androidx.annotation.Px
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LineHeight
import com.example.myapplication.ui.util.toPx
import kotlin.math.roundToInt

open class LineHeightSpanRenderer(private val context: Context) : SpanRenderer<LineHeight> {

    override fun renderSpan(styleSpan: LineHeight): Any {
        return LineHeightSpan(height = styleSpan.height.toPx(context = context))
    }

    private class LineHeightSpan(@Px private val height: Int) : android.text.style.LineHeightSpan {

        override fun chooseHeight(
            text: CharSequence,
            start: Int,
            end: Int,
            spanstartv: Int,
            lineHeight: Int,
            fm: Paint.FontMetricsInt
        ) {
            val originHeight = fm.descent - fm.ascent
            if (originHeight <= 0) {
                return
            }
            val ratio = height * 1.0f / originHeight
            fm.descent = (fm.descent * ratio).roundToInt()
            fm.ascent = fm.descent - height
        }
    }
}
