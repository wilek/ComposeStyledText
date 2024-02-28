/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.text.Layout
import android.text.Spanned
import android.text.style.LeadingMarginSpan
import androidx.annotation.ColorInt
import androidx.annotation.Px
import eu.wilek.textcombine.TextCombine.DimensionValue
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Bullet
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toColor
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

internal class BulletSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as Bullet
        return BulletPointSpan(
            gapWidth = (styleSpan.gapWidth ?: DEFAULT_GAP_WIDTH).toPx(context = context),
            bulletRadius = (styleSpan.radius ?: DEFAULT_BULLET_RADIUS).toPx(context = context),
            bulletColor = styleSpan.color?.toColor(context = context) ?: Color.BLACK
        )
    }

    private companion object {
        val DEFAULT_GAP_WIDTH = DimensionValue.FromDp(value = 8f)
        val DEFAULT_BULLET_RADIUS = DimensionValue.FromDp(value = 4f)
    }

    private class BulletPointSpan(
        @Px private val gapWidth: Float,
        @Px private val bulletRadius: Float,
        @ColorInt private val bulletColor: Int
    ) : LeadingMarginSpan {

        private val useColor = bulletColor != Color.BLACK
        private val bulletPath: Path by lazy(LazyThreadSafetyMode.NONE) { Path() }

        override fun getLeadingMargin(first: Boolean): Int {
            return (2 * bulletRadius + 2 * gapWidth).roundToInt()
        }

        override fun drawLeadingMargin(
            canvas: Canvas,
            paint: Paint,
            currentMarginLocation: Int,
            paragraphDirection: Int,
            lineTop: Int,
            lineBaseline: Int,
            lineBottom: Int,
            text: CharSequence,
            lineStart: Int,
            lineEnd: Int,
            isFirstLine: Boolean,
            layout: Layout
        ) {
            if ((text as Spanned).getSpanStart(this) == lineStart) {
                paint.withCustomColor {
                    if (canvas.isHardwareAccelerated) {
                        bulletPath.addCircle(0f, 0.0f, bulletRadius, Path.Direction.CW)
                        val save = canvas.save()
                        canvas.translate(
                            getCircleXLocation(currentMarginLocation, paragraphDirection),
                            getCircleYLocation(lineTop, lineBottom)
                        )
                        canvas.drawPath(bulletPath, paint)
                        canvas.restoreToCount(save)
                    } else {
                        canvas.drawCircle(
                            getCircleXLocation(currentMarginLocation, paragraphDirection),
                            getCircleYLocation(lineTop, lineBottom),
                            bulletRadius,
                            paint
                        )
                    }
                }
            }
        }

        private fun getCircleYLocation(lineTop: Int, lineBottom: Int): Float {
            return (lineTop + lineBottom) / 2.0f
        }

        private fun getCircleXLocation(currentMarginLocation: Int, paragraphDirection: Int): Float {
            return currentMarginLocation + paragraphDirection * bulletRadius
        }

        private inline fun Paint.withCustomColor(block: () -> Unit) {
            val oldStyle = style
            val oldColor = if (useColor) color else Color.TRANSPARENT

            if (useColor) {
                color = this@BulletPointSpan.bulletColor
            }

            style = Paint.Style.FILL

            block()

            if (useColor) {
                color = oldColor
            }

            style = oldStyle
        }
    }
}
