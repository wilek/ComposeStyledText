package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ReplacementSpan
import androidx.annotation.IntRange
import eu.wilek.textcombine.TextCombine.ImageAlignType
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Image
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toImage

open class ImageSpanRenderer(private val context: Context) : SpanRenderer<Image> {

    override fun renderSpan(
        styleSpan: Image
    ): Any {
        return ImageSpan(
            drawable = styleSpan.image.toImage(context = context, size = styleSpan.size, margin = styleSpan.margin),
            alignment = styleSpan.alignType
        )
    }

    private class ImageSpan(
        private val drawable: Drawable,
        private val alignment: ImageAlignType
    ) : ReplacementSpan() {

        override fun getSize(
            paint: Paint,
            text: CharSequence?,
            @IntRange(from = 0) start: Int,
            @IntRange(from = 0) end: Int,
            fm: Paint.FontMetricsInt?
        ): Int {
            val rect = drawable.bounds

            if (fm != null) {
                val fontMetrics = paint.fontMetricsInt
                val fontHeight = fontMetrics.descent - fontMetrics.ascent
                val drawableHeight = rect.bottom - rect.top
                val centerY = fontMetrics.ascent + fontHeight / 2
                fm.ascent = centerY - drawableHeight / 2
                fm.top = fm.ascent
                fm.bottom = centerY + drawableHeight / 2
                fm.descent = fm.bottom
            }

            return drawable.bounds.right
        }

        override fun draw(
            canvas: Canvas,
            text: CharSequence?,
            @IntRange(from = 0) start: Int,
            @IntRange(from = 0) end: Int,
            x: Float,
            top: Int,
            y: Int,
            bottom: Int,
            paint: Paint
        ) {
            canvas.save()

            val transY = when (alignment) {
                ImageAlignType.ALIGN_BASELINE -> calculateBaselineTransY(bottom = bottom, paint = paint)
                ImageAlignType.ALIGN_CENTER -> calculateCenterTransY(y = y, paint = paint)
                ImageAlignType.ALIGN_BOTTOM -> calculateBottomTransY(bottom = bottom)
            }

            canvas.translate(x, transY.toFloat())
            drawable.draw(canvas)
            canvas.restore()
        }

        private fun calculateBaselineTransY(
            bottom: Int,
            paint: Paint
        ): Int {
            return bottom - drawable.bounds.bottom - paint.fontMetricsInt.descent
        }

        private fun calculateCenterTransY(
            y: Int,
            paint: Paint
        ): Int {
            val fontMetrics = paint.fontMetricsInt
            val fontHeight = fontMetrics.descent - fontMetrics.ascent
            val centerY = y + fontMetrics.descent - fontHeight / 2
            return centerY - (drawable.bounds.bottom - drawable.bounds.top) / 2
        }

        private fun calculateBottomTransY(
            bottom: Int,
        ): Int {
            return bottom - drawable.bounds.bottom
        }
    }
}
