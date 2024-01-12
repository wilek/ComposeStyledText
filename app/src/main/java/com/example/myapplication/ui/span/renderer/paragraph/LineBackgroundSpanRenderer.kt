package com.example.myapplication.ui.span.renderer.paragraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.annotation.Px
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LineBackground
import com.example.myapplication.ui.util.toColor

open class LineBackgroundSpanRenderer : SpanRenderer<LineBackground> {

    override fun renderSpan(context: Context, styleSpan: LineBackground): Any {
        return LineBackgroundSpan(styleSpan.color.toColor(context = context))
    }

    private class LineBackgroundSpan(@ColorInt private val color: Int) : android.text.style.LineBackgroundSpan {

        override fun drawBackground(
            canvas: Canvas,
            paint: Paint,
            @Px left: Int,
            @Px right: Int,
            @Px top: Int,
            @Px baseline: Int,
            @Px bottom: Int,
            text: CharSequence,
            start: Int,
            end: Int,
            lineNumber: Int
        ) {
            val originColor = paint.color
            paint.color = color
            canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
            paint.color = originColor
        }
    }
}
