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

package com.example.myapplication.ui.span.renderer.paragraph

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
import androidx.core.graphics.withTranslation
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Bullet
import com.example.myapplication.ui.util.toColor
import com.example.myapplication.ui.util.toPx

open class BulletSpanRenderer : SpanRenderer<Bullet> {

    override fun renderSpan(context: Context, styleSpan: Bullet): Any {
        return BulletPointSpan(
            gapWidth = styleSpan.gapWidth?.toPx(context = context) ?: DEFAULT_GAP_WIDTH,
            bulletRadius = styleSpan.radius?.toPx(context = context) ?: DEFAULT_BULLET_RADIUS,
            bulletColor = styleSpan.color?.toColor(context = context) ?: Color.BLACK
        )
    }

    private companion object {
        const val DEFAULT_GAP_WIDTH = 2
        const val DEFAULT_BULLET_RADIUS = 15
    }

    private class BulletPointSpan(
        @Px private val gapWidth: Int,
        @Px private val bulletRadius: Int,
        @ColorInt private val bulletColor: Int
    ) : LeadingMarginSpan {

        private val useColor = bulletColor != Color.BLACK
        private val bulletPath: Path by lazy(LazyThreadSafetyMode.NONE) { Path() }

        override fun getLeadingMargin(first: Boolean): Int {
            return (2 * bulletRadius + 2 * gapWidth)
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
                        bulletPath.addCircle(0.0f, 0.0f, 1.2f * DEFAULT_BULLET_RADIUS, Path.Direction.CW)

                        canvas.withTranslation(
                            getCircleXLocation(currentMarginLocation, paragraphDirection),
                            getCircleYLocation(lineTop, lineBottom)
                        ) {
                            drawPath(bulletPath, paint)
                        }
                    } else {
                        canvas.drawCircle(
                            getCircleXLocation(currentMarginLocation, paragraphDirection),
                            getCircleYLocation(lineTop, lineBottom),
                            bulletRadius.toFloat(),
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
            return gapWidth + currentMarginLocation + paragraphDirection * bulletRadius.toFloat()
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
