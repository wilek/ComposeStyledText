package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import androidx.annotation.Px
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineBackground
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toColor

open class LineBackgroundSpanRenderer(private val context: Context) : SpanRenderer<LineBackground> {

    override fun renderSpan(styleSpan: LineBackground): Any {
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
