package com.example.myapplication.ui.string.combine

import android.text.style.BulletSpan
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.TextCombine.TextValue
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan
import com.example.myapplication.ui.string.combine.TextCombine.ImageSource
import com.example.myapplication.ui.string.combine.TextCombine.BitmapSource
import com.example.myapplication.ui.string.combine.TextCombine.Size
import com.example.myapplication.ui.string.combine.TextCombine.Margin
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Bullet
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LineBackground
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.LineHeight
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.Quote
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import com.example.myapplication.ui.string.combine.TextCombine.TextAlignmentType
import com.example.myapplication.ui.string.combine.TextCombine.DimensionValue
import com.example.myapplication.ui.string.combine.TextCombine.ColorSource

fun stringCombine(builder: StringCombineBuilder.() -> Unit): TextCombine {
    return StringCombineBuilder().apply(builder).build()
}

class StringCombineBuilder {

    private val texts = mutableListOf<TextValue>()

    fun appendString(text: String, builder: StringTextValueBuilder.() -> Unit = {}) {
        texts.add(StringTextValueBuilder(text = text).apply(builder).build())
    }

    fun appendStringResource(@StringRes resourceId: Int, builder: StringResourceTextValueBuilder.() -> Unit = {}) {
        texts.add(StringResourceTextValueBuilder(resourceId = resourceId).apply(builder).build())
    }

    fun appendStringPluralResource(
        @PluralsRes resourceId: Int,
        count: Int,
        builder: StringPluralResourceTextValueBuilder.() -> Unit = {}
    ) {
        texts.add(StringPluralResourceTextValueBuilder(resourceId = resourceId, count = count).apply(builder).build())
    }

    fun build(): TextCombine {
        return TextCombine(texts = texts)
    }
}

abstract class TextValueBuilder {

    protected abstract val source: TextCombine.TextSource
    protected abstract val spans: List<StyleSpan>
    private val formatArgs = mutableListOf<TextValue>()

    fun formatWithString(text: String, builder: StringTextValueBuilder.() -> Unit = {}) {
        formatArgs.add(StringTextValueBuilder(text = text).apply(builder).build())
    }

    fun formatWithStringResource(@StringRes resourceId: Int, builder: StringResourceTextValueBuilder.() -> Unit = {}) {
        formatArgs.add(StringResourceTextValueBuilder(resourceId = resourceId).apply(builder).build())
    }

    fun formatWithStringPluralResource(
        @PluralsRes resourceId: Int,
        count: Int,
        builder: StringPluralResourceTextValueBuilder.() -> Unit = {}
    ) {
        formatArgs.add(StringPluralResourceTextValueBuilder(resourceId = resourceId, count = count).apply(builder).build())
    }

    fun build(): TextValue {
        return TextValue(
            source = source,
            formatArgs = formatArgs,
            spans = spans
        )
    }
}

class StringTextValueBuilder(text: String): TextValueBuilder() {
    override val source = FromString(text = text)
    override val spans = mutableListOf<StyleSpan>()

    fun setSpans(builder: SpanBuilder.() -> Unit = {}) {
        spans.addAll(SpanBuilder().apply(builder).build())
    }
}

class StringResourceTextValueBuilder(@StringRes resourceId: Int) : TextValueBuilder() {
    override var source = FromStringResource(stringResId = resourceId)
    override val spans = mutableListOf<StyleSpan>()

    fun setSpans(builder: SpanBuilder.() -> Unit = {}) {
        spans.addAll(SpanBuilder().apply(builder).build())
    }
}

class StringPluralResourceTextValueBuilder(@PluralsRes resourceId: Int, count: Int) : TextValueBuilder() {
    override var source = FromStringPluralResource(pluralResId = resourceId, count = count)
    override val spans = mutableListOf<StyleSpan>()

    fun setSpans(builder: SpanBuilder.() -> Unit = {}) {
        spans.addAll(SpanBuilder().apply(builder).build())
    }
}

class SpanBuilder {
    private val spans = mutableListOf<StyleSpan>()

    fun paragraph(builder: ParagraphSpanBuilder.() -> Unit = {}) {
        spans.addAll(ParagraphSpanBuilder().apply(builder).build())
    }


    fun alignment(alignment: TextAlignmentType) {
        spans.add(Alignment(alignment = alignment))
    }

    fun bullet(builder: (BulletSpanBuilder.() -> Unit)? = null) {
        val spanBuilder = builder?.let { BulletSpanBuilder().apply(it) }
        spans.add(Bullet(gapWidth = spanBuilder?.gapWidth, color = spanBuilder?.color, radius = spanBuilder?.radius))
    }

    fun leadingImage(image: ImageSource, builder: (LeadingImageSpanBuilder.() -> Unit)? = null) {
        val spanBuilder = builder?.let { LeadingImageSpanBuilder().apply(it) }
        spans.add(LeadingImage(image = image, size = spanBuilder?.size, margin = spanBuilder?.margin))
    }

    fun leadingMargin(first: Int, rest: Int) {
        spans.add(LeadingMargin(first = first, rest = rest))
    }

    fun lineBackground(color: ColorSource) {
        spans.add(LineBackground(color = color))
    }

    fun lineHeight(height: DimensionValue) {
        spans.add(LineHeight(height = height))
    }

    fun quote(builder: (QuoteSpanBuilder.() -> Unit)? = null) {
        val spanBuilder = builder?.let { QuoteSpanBuilder().apply(it) }
        spans.add(Quote(color = spanBuilder?.color, stripeWidth = spanBuilder?.stripeWidth, gapWidth = spanBuilder?.gapWidth))
    }

    fun tabStop(offset: DimensionValue) {
        spans.add(TabStop(offset = offset))
    }

    fun boldItalic() {
        //spans.add(TextSpan.CharacterStyle.BoldItalic)
    }

    fun italic() {
        //spans.add(TextSpan.CharacterStyle.Italic)
    }

    fun underline() {
        spans.add(StyleSpan.CharacterStyle.Underline)
    }

    fun strikethrough() {
        spans.add(StyleSpan.CharacterStyle.Strikethrough)
    }

    fun relativeSize(proportion: Float) {
        spans.add(StyleSpan.CharacterStyle.RelativeSize(proportion = proportion))
    }

    fun foregroundColor(builder: ColorBuilder.() -> Unit = {}) {
        spans.add(StyleSpan.CharacterStyle.ForegroundColor(color = ColorBuilder().apply(builder).build()))
    }

    fun backgroundColor(builder: ColorBuilder.() -> Unit = {}) {
        spans.add(StyleSpan.CharacterStyle.BackgroundColor(color = ColorBuilder().apply(builder).build()))
    }

    fun build(): List<StyleSpan> {
        return spans
    }
}

class ParagraphSpanBuilder {

    private val spans = mutableListOf<StyleSpan>()

    fun alignment(alignment: TextAlignmentType) {
        spans.add(Alignment(alignment = alignment))
    }

    fun build(): List<StyleSpan> {
        return spans
    }
}

class LeadingImageSpanBuilder {

    internal var size: Size? = null
    internal var margin: Margin? = null

    fun size(builder: SizeBuilder.() -> Unit) {
        val sizeBuilder = SizeBuilder().apply(builder)
        size = Size(width = sizeBuilder.width, height = sizeBuilder.height)
    }

    fun margin(builder: MarginBuilder.() -> Unit) {
        val marginBuilder = MarginBuilder().apply(builder)
        margin = Margin(start = marginBuilder.start, top = marginBuilder.top, bottom = marginBuilder.bottom, end = marginBuilder.end)
    }
}

class ImageSourceBuilder {

    lateinit var imageSource: ImageSource

    fun fromDrawable(@DrawableRes drawableResId: Int) {
        imageSource = ImageSource.FromDrawable(drawableResId = drawableResId)
    }

    fun fromAssets(fileName: String) {
        imageSource = ImageSource.FromBitmap(bitmapSource = BitmapSource.FromAssets(fileName = fileName))
    }

    fun fromUri(uri: String) {
        imageSource = ImageSource.FromBitmap(bitmapSource = BitmapSource.FromUri(uri = uri))
    }

    fun build(): ImageSource {
        return imageSource
    }
}

fun imageFromDrawable(@DrawableRes drawableResId: Int): ImageSource {
    return ImageSource.FromDrawable(drawableResId = drawableResId)
}

class ColorBuilder {

    private lateinit var colorSource: ColorSource

    fun fromString(hexColor: String) {
        colorSource = ColorSource.FromString(color = hexColor)
    }

    fun fromInt(color: Int) {
        colorSource = ColorSource.FromInt(color = color)
    }

    fun fromResource(@ColorRes colorResId: Int) {
        colorSource = ColorSource.FromResources(colorResId = colorResId)
    }

    fun build(): ColorSource {
        return colorSource
    }
}

class SizeBuilder {

    var width: DimensionValue? = null
    var height: DimensionValue? = null
}

class MarginBuilder {

    var start: DimensionValue? = null
    var top: DimensionValue? = null
    var bottom: DimensionValue? = null
    var end: DimensionValue? = null
}

class BulletSpanBuilder {

    var gapWidth: DimensionValue? = null
    var color: ColorSource? = null
    var radius: DimensionValue? = null
}

class QuoteSpanBuilder {

    var color: ColorSource? = null
    var stripeWidth: DimensionValue? = null
    var gapWidth: DimensionValue? = null
}

fun colorFromString(hexColor: String): ColorSource {
    return ColorSource.FromString(color = hexColor)
}

fun colorFromInt(color: Int): ColorSource {
    return ColorSource.FromInt(color = color)
}

fun colorFromResource(@ColorRes colorResId: Int): ColorSource {
    return ColorSource.FromResources(colorResId = colorResId)
}

fun dimensionFromPx(@Dimension(unit = Dimension.PX) value: Int): DimensionValue {
    return DimensionValue.FromPx(value = value)
}

fun dimensionFromDp(@Dimension(unit = Dimension.DP) value: Int): DimensionValue {
    return DimensionValue.FromDp(value = value)
}

fun dimensionFromSp(@Dimension(unit = Dimension.SP) value: Float): DimensionValue {
    return DimensionValue.FromSp(value = value)
}

fun dimensionFromResource(@DimenRes dimenResId: Int): DimensionValue {
    return DimensionValue.FromResource(dimenResId = dimenResId)
}
