package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.graphics.Paint
import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Typeface
import eu.wilek.textcombine.TextCombine.TypefaceSource.FromAssets
import eu.wilek.textcombine.TextCombine.TypefaceSource.FromFamilyName
import eu.wilek.textcombine.TextCombine.TypefaceSource.FromResources
import eu.wilek.textcombine.renderer.span.SpanRenderer

internal class TypefaceSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as Typeface
        return when (val typeface = styleSpan.typeface) {
            is FromAssets -> TypefaceSpan(typeface = context.getFontFromAsset(fileName = typeface.fileName))
            is FromFamilyName -> FamilyNameTypefaceSpan(familyName = typeface.familyName)
            is FromResources -> TypefaceSpan(typeface = context.getFontFromResources(fontResId = typeface.fontResId))
        }
    }

    private fun Context.getFontFromAsset(fileName: String) = android.graphics.Typeface.createFromAsset(assets, fileName)

    private fun Context.getFontFromResources(@FontRes fontResId: Int) = ResourcesCompat.getFont(this, fontResId)!!

    private class TypefaceSpan(private val typeface: android.graphics.Typeface) : MetricAffectingSpan() {
        override fun updateDrawState(textPaint: TextPaint) {
            textPaint.typeface = typeface
        }

        override fun updateMeasureState(textPaint: TextPaint) {
            textPaint.typeface = typeface
        }
    }

    private class FamilyNameTypefaceSpan(private val familyName: String) : MetricAffectingSpan() {
        override fun updateDrawState(textPaint: TextPaint) {
            applyFontFamily(paint = textPaint, family = familyName)
        }

        override fun updateMeasureState(textPaint: TextPaint) {
            applyFontFamily(paint = textPaint, family = familyName)
        }

        private fun applyFontFamily(paint: Paint, family: String) {
            val style = when (paint.typeface) {
                null -> android.graphics.Typeface.NORMAL
                else -> paint.typeface.style
            }

            val styledTypeface = android.graphics.Typeface.create(family, style)
            val fake = style and styledTypeface.style.inv()

            if (fake and android.graphics.Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and android.graphics.Typeface.ITALIC != 0) {
                paint.textSkewX = TEXT_SKEW_X
            }

            paint.setTypeface(styledTypeface)
        }

        private companion object {
            const val TEXT_SKEW_X = -0.25f
        }
    }
}
