package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.Layout
import android.text.style.LeadingMarginSpan
import androidx.annotation.ColorInt
import androidx.annotation.Px
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Quote
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toColor
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

open class QuoteSpanRenderer(private val context: Context) : SpanRenderer<Quote> {

    override fun renderSpan(styleSpan: Quote): Any {
        return QuoteSpan(
            gapWidth = (styleSpan.gapWidth ?: DEFAULT_GAP_WIDTH).toPx(context = context),
            stripeWidth = (styleSpan.stripeWidth ?: DEFAULT_STRIPE_WIDTH).toPx(context = context),
            color = styleSpan.color?.toColor(context = context) ?: Color.BLACK
        )
    }

    private companion object {
        val DEFAULT_GAP_WIDTH = TextCombine.DimensionValue.FromDp(value = 2f)
        val DEFAULT_STRIPE_WIDTH = TextCombine.DimensionValue.FromDp(value = 2f)
    }

    private class QuoteSpan(
        @Px private val gapWidth: Float,
        @Px private val stripeWidth: Float,
        @ColorInt private val color: Int
    ) : LeadingMarginSpan {

        override fun getLeadingMargin(first: Boolean): Int {
            return (gapWidth + stripeWidth).roundToInt()
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

            canvas.drawRect(x.toFloat(), top.toFloat(), x + dir * stripeWidth, bottom.toFloat(), paint)

            paint.style = paintStyle
            paint.color = paintColor
        }
    }
}
