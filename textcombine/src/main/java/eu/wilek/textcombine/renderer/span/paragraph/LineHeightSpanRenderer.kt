package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.graphics.Paint
import androidx.annotation.Px
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineHeight
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

internal class LineHeightSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as LineHeight
        return LineHeightSpan(height = styleSpan.height.toPx(context = context).roundToInt())
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
