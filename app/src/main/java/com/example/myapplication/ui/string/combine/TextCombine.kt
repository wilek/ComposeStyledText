package com.example.myapplication.ui.string.combine

import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.FontRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes

data class TextCombine(val texts: List<TextValue>) {

    data class TextValue(
        val source: TextSource,
        val formatArgs: List<TextValue> = emptyList(),
        val span: List<StyleSpan> = emptyList()
    )

    sealed class TextSource {
        data class FromString(val text: String) : TextSource()
        data class FromStringResource(@StringRes val resourceId: Int) : TextSource()
        data class FromStringPluralResource(@PluralsRes val resourceId: Int, val count: Int) : TextSource()
    }

    sealed class StyleSpan {
        sealed class CharacterStyle : StyleSpan() {
            data class AbsoluteSize(val size: DimensionValue): CharacterStyle()
            data class BackgroundColor(val color: ColorSource) : CharacterStyle()
            data class Clickable(val id: String?, val onClick: (id: String?) -> Unit) : CharacterStyle()
            data class ForegroundColor(val color: ColorSource) : CharacterStyle()
            data class Image(
                val image: ImageSource,
                val alignType: ImageAlignType,
                val size: Size? = null,
                val margin: Margin? = null
            ): CharacterStyle()

            data class MaskFilter(val filterType: MaskFilterType): CharacterStyle()

            data class RelativeSize(@FloatRange(from = 0.0) val proportion: Float) : CharacterStyle()

            data class ScaleX(@FloatRange(from = 0.0) val proportion: Float) : CharacterStyle()

            object Strikethrough : CharacterStyle()
            data class Style(val typefaceStyle: TypefaceStyle) : CharacterStyle()
            object Subscript : CharacterStyle()
            object Superscript : CharacterStyle()
            data class TextAppearance(val type: TextAppearanceType) : CharacterStyle()
            data class Typeface(val typeface: TypefaceSource) : CharacterStyle()
            object Underline : CharacterStyle()
        }

        sealed class ParagraphStyle : StyleSpan() {
            data class Alignment(val alignment: TextAlignmentType): ParagraphStyle()
            data class Bullet(
                val gapWidth: DimensionValue? = null,
                val color: ColorSource? = null,
                val radius: DimensionValue? = null
            ) : ParagraphStyle()
            data class LeadingImage(
                val image: ImageSource,
                val size: Size? = null,
                val margin: Margin? = null
            ) : ParagraphStyle()

            data class LeadingMargin(val first: Int, val rest: Int): ParagraphStyle()
            data class LineBackground(val color: ColorSource): ParagraphStyle()
            data class LineHeight(val height: DimensionValue): ParagraphStyle()
            data class Quote(
                val color: ColorSource? = null,
                val stripeWidth: DimensionValue? = null,
                val gapWidth: DimensionValue? = null
            ) : ParagraphStyle()
            data class TabStop(val offset: DimensionValue): ParagraphStyle()
        }
    }

    sealed class ColorSource {
        data class FromString(val color: String) : ColorSource()
        data class FromInt(@ColorInt val color: Int) : ColorSource()
        data class FromResources(@ColorRes val resourceId: Int) : ColorSource()
    }

    sealed class ImageSource {
        data class FromBitmap(val bitmapSource: BitmapSource): ImageSource()
        data class FromDrawable(@DrawableRes val drawableResId: Int): ImageSource()
    }

    sealed class BitmapSource {
        data class FromAssets(val fileName: String) : BitmapSource()
        data class FromUri(val uri: String) : BitmapSource()
    }

    data class Size(val width: DimensionValue? = null, val height: DimensionValue? = null)

    data class Margin(
        val start: DimensionValue? = null,
        val top: DimensionValue? = null,
        val bottom: DimensionValue? = null,
        val end: DimensionValue? = null
    )

    sealed class DimensionValue {
        data class FromPx(@Dimension(unit = Dimension.PX) val value: Int) : DimensionValue()
        data class FromDp(@Dimension(unit = Dimension.DP) val value: Int) : DimensionValue()
        data class FromSp(@Dimension(unit = Dimension.SP) val value: Float) : DimensionValue()
        data class FromResource(@DimenRes val dimenResId: Int) : DimensionValue()
    }

    enum class TypefaceStyle {
        NORMAL,
        BOLD,
        ITALIC,
        BOLD_ITALIC
    }

    enum class TextAlignmentType {
        ALIGN_NORMAL,
        ALIGN_OPPOSITE,
        ALIGN_CENTER
    }

    enum class BlurType {
        NORMAL,
        SOLID,
        OUTER,
        INNER
    }

    enum class ImageAlignType {
        ALIGN_BOTTOM,
        ALIGN_BASELINE,
        ALIGN_CENTER
    }

    sealed class MaskFilterType {
        data class Blur(val blurType: BlurType, @FloatRange(from = 0.0) val radius: Float) : MaskFilterType()
    }

    sealed class TextAppearanceType {
        data class FromResources(
            @StyleRes val appearanceResId: Int,
            @StyleableRes val colorListResId: Int = -1
        ) : TextAppearanceType()

        data class FromSpecified(
            val typefaceFamily: String,
            val typefaceStyle: TypefaceStyle,
            val textSize: DimensionValue,
            val textColor: List<ColorState>,
            val textLinkColor: List<ColorState>
        ) : TextAppearanceType()
    }

    sealed class ColorState(@AttrRes val stateResIds: IntArray, val color: ColorSource)

    sealed class TypefaceSource {
        data class FromAssets(val fileName: String) : TypefaceSource()
        data class FromResources(@FontRes val fontResId: Int, val style: TypefaceStyle = TypefaceStyle.NORMAL) : TypefaceSource()
        data class FromFamilyName(val familyName: String) : TypefaceSource()
    }
}
