package com.example.myapplication.ui.span.paragraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.style.LeadingMarginSpan
import androidx.annotation.ColorInt
import androidx.annotation.Px
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Quote
import com.example.myapplication.ui.util.toColor
import com.example.myapplication.ui.util.toPx

open class QuoteSpanCreator : TextCombineSpanCreator<Quote> {

    override fun createSpan(context: Context, styleSpan: Quote): Any {
        return QuoteSpan(
            gapWidth = styleSpan.gapWidth?.toPx(context = context) ?: STANDARD_GAP_WIDTH_PX,
            stripeWidth = styleSpan.stripeWidth?.toPx(context = context) ?: STANDARD_STRIPE_WIDTH_PX,
            color = styleSpan.color?.toColor(context = context) ?: Color.BLACK
        )
    }

    private companion object {
        const val STANDARD_GAP_WIDTH_PX = 2
        const val STANDARD_STRIPE_WIDTH_PX = 2
    }

    private class QuoteSpan(
        @Px private val gapWidth: Int,
        @Px private val stripeWidth: Int,
        @ColorInt private val color: Int
    ) : LeadingMarginSpan {

        override fun getLeadingMargin(first: Boolean): Int {
            return stripeWidth + gapWidth
        }

        override fun drawLeadingMargin(
            canvas: Canvas,
            paint: Paint,
            x: Int,
            dir: Int,
            @Px top: Int,
            @Px baseline: Int,
            @Px bottom: Int,
            text: CharSequence,
            start: Int,
            end: Int,
            first: Boolean,
            layout: Layout
        ) {
            val paintStyle = paint.style
            val paintColor = paint.color

            paint.style = Paint.Style.FILL
            paint.color = color

            canvas.drawRect(x.toFloat(), top.toFloat(), (x + dir * stripeWidth).toFloat(), bottom.toFloat(), paint)

            paint.style = paintStyle
            paint.color = paintColor
        }
    }
}
