package com.example.myapplication.ui.span.character

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ReplacementSpan
import androidx.annotation.IntRange
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.ImageAlignType
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Image
import com.example.myapplication.ui.util.toImage

open class ImageSpanCreator : TextCombineSpan<Image> {
    override fun createSpan(context: Context, styleSpan: Image): Any {
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
                fm.ascent = -rect.bottom
                fm.descent = 0
                fm.top = fm.ascent
                fm.bottom = 0
            }

            return rect.right
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
                ImageAlignType.ALIGN_BASELINE -> bottom - drawable.bounds.bottom - paint.fontMetricsInt.descent
                ImageAlignType.ALIGN_CENTER -> top + (bottom - top) / 2 - drawable.bounds.height() / 2
                ImageAlignType.ALIGN_BOTTOM -> bottom - drawable.bounds.bottom
            }

            canvas.translate(x, transY.toFloat())
            drawable.draw(canvas)
            canvas.restore()
        }
    }
}
