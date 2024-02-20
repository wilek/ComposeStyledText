package eu.wilek.textcombine.dsl

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
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.BitmapSource
import eu.wilek.textcombine.TextCombine.BlurType
import eu.wilek.textcombine.TextCombine.ColorSource
import eu.wilek.textcombine.TextCombine.DimensionValue
import eu.wilek.textcombine.TextCombine.ImageAlignType
import eu.wilek.textcombine.TextCombine.ImageSource
import eu.wilek.textcombine.TextCombine.Margin
import eu.wilek.textcombine.TextCombine.MaskFilterType
import eu.wilek.textcombine.TextCombine.Size
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Clickable
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Image
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.MaskFilter
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.RelativeSize
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ScaleX
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Strikethrough
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Style
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Subscript
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Superscript
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.TextAppearance
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Typeface
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Underline
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Alignment
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Bullet
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineBackground
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LineHeight
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.Quote
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import eu.wilek.textcombine.TextCombine.TextAlignmentType
import eu.wilek.textcombine.TextCombine.TextSource.FromString
import eu.wilek.textcombine.TextCombine.TextSource.FromStringPluralResource
import eu.wilek.textcombine.TextCombine.TextSource.FromStringResource
import eu.wilek.textcombine.TextCombine.TextValue
import eu.wilek.textcombine.TextCombine.TypefaceSource
import eu.wilek.textcombine.TextCombine.TypefaceStyle

@DslMarker
@Target(
    AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.TYPE,
    AnnotationTarget.CLASS
)
annotation class TextCombineDsl

@TextCombineDsl
fun stringCombine(builder: (@TextCombineDsl StringCombineBuilder).() -> Unit): TextCombine {
    return StringCombineBuilder().apply(builder).build()
}

class StringCombineBuilder {

    private val texts = mutableListOf<TextValue>()

    fun appendString(text: String, builder: (@TextCombineDsl StringTextValueBuilder).() -> Unit = {}) {
        texts.add(StringTextValueBuilder(text = text).apply(builder).build())
    }

    fun appendStringResource(
        @StringRes resourceId: Int,
        builder: (@TextCombineDsl StringResourceTextValueBuilder).() -> Unit = {}
    ) {
        texts.add(StringResourceTextValueBuilder(resourceId = resourceId).apply(builder).build())
    }

    fun appendStringPluralResource(
        @PluralsRes resourceId: Int,
        count: Int,
        builder: (@TextCombineDsl StringPluralResourceTextValueBuilder).() -> Unit = {}
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

    fun formatWithString(text: String, builder: (@TextCombineDsl StringTextValueBuilder).() -> Unit = {}) {
        formatArgs.add(StringTextValueBuilder(text = text).apply(builder).build())
    }

    fun formatWithStringResource(
        @StringRes resourceId: Int,
        builder: (@TextCombineDsl StringResourceTextValueBuilder).() -> Unit = {}
    ) {
        formatArgs.add(StringResourceTextValueBuilder(resourceId = resourceId).apply(builder).build())
    }

    fun formatWithStringPluralResource(
        @PluralsRes resourceId: Int,
        count: Int,
        builder: (@TextCombineDsl StringPluralResourceTextValueBuilder).() -> Unit = {}
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

@TextCombineDsl
class StringTextValueBuilder(text: String) : TextValueBuilder() {
    override val source = FromString(text = text)
    override val spans = mutableListOf<StyleSpan>()

    fun setSpans(builder: (@TextCombineDsl SpanBuilder).() -> Unit = {}) {
        spans.addAll(SpanBuilder().apply(builder).build())
    }
}

class StringResourceTextValueBuilder(@StringRes resourceId: Int) : TextValueBuilder() {
    override var source = FromStringResource(stringResId = resourceId)
    override val spans = mutableListOf<StyleSpan>()

    fun setSpans(builder: (@TextCombineDsl SpanBuilder).() -> Unit = {}) {
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

interface SpanBuilderContext

class SpanBuilder : SpanBuilderContext {

    private val spans = mutableListOf<StyleSpan>()

    fun absoluteSize(size: DimensionValue) {
        spans.add(AbsoluteSize(size = size))
    }

    fun backgroundColor(color: ColorSource) {
        spans.add(StyleSpan.CharacterStyle.BackgroundColor(color = color))
    }

    fun clickable(onClick: (id: String?) -> Unit, id: String? = null) {
        spans.add(Clickable(onClick = onClick, id = id))
    }

    fun foregroundColor(color: ColorSource) {
        spans.add(StyleSpan.CharacterStyle.ForegroundColor(color = color))
    }

    fun image(
        image: ImageSource,
        alignType: ImageAlignType,
        builder: ((@TextCombineDsl ImageSpanBuilder).() -> Unit)? = null
    ) {
        val spanBuilder = builder?.let { ImageSpanBuilder().apply(it) }
        spans.add(
            Image(
                image = image,
                alignType = alignType,
                size = spanBuilder?.size,
                margin = spanBuilder?.margin
            )
        )
    }

    fun maskFilter(filterType: MaskFilterType) {
        spans.add(MaskFilter(filterType = filterType))
    }

    fun relativeSize(@FloatRange(from = 0.0) proportion: Float) {
        spans.add(RelativeSize(proportion = proportion))
    }

    fun scaleX(@FloatRange(from = 0.0) proportion: Float) {
        spans.add(ScaleX(proportion = proportion))
    }

    fun strikethrough() {
        spans.add(Strikethrough)
    }

    fun style(typefaceStyle: TypefaceStyle) {
        spans.add(Style(typefaceStyle = typefaceStyle))
    }

    fun subscript() {
        spans.add(Subscript)
    }

    fun superscript() {
        spans.add(Superscript)
    }

    fun textAppearance(@StyleRes appearanceResId: Int, builder: (TextAppearanceBuilder.() -> Unit)? = null) {
        val spanBuilder = builder?.let { TextAppearanceBuilder().apply(it) }
        spans.add(TextAppearance(appearanceResId = appearanceResId, colorListResId = spanBuilder?.colorListResId ?: -1))

    }

    fun typeface(typeface: TypefaceSource) {
        spans.add(Typeface(typeface = typeface))
    }

    fun underline() {
        spans.add(Underline)
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

    fun build(): List<StyleSpan> {
        return spans
    }
}

@TextCombineDsl
class LeadingImageSpanBuilder {

    internal var size: Size? = null
    internal var margin: Margin? = null

    fun size(builder: SizeBuilder.() -> Unit) {
        val sizeBuilder = SizeBuilder().apply(builder)
        size = Size(width = sizeBuilder.width, height = sizeBuilder.height)
    }

    fun margin(builder: (@TextCombineDsl MarginBuilder).() -> Unit) {
        val marginBuilder = MarginBuilder().apply(builder)
        margin =
            Margin(start = marginBuilder.start, top = marginBuilder.top, bottom = marginBuilder.bottom, end = marginBuilder.end)
    }
}


class ImageSpanBuilder {

    internal var size: Size? = null
    internal var margin: Margin? = null

    fun size(builder: (@TextCombineDsl SizeBuilder).() -> Unit) {
        val sizeBuilder = SizeBuilder().apply(builder)
        size = Size(width = sizeBuilder.width, height = sizeBuilder.height)
    }

    fun margin(builder: MarginBuilder.() -> Unit) {
        val marginBuilder = MarginBuilder().apply(builder)
        margin =
            Margin(start = marginBuilder.start, top = marginBuilder.top, bottom = marginBuilder.bottom, end = marginBuilder.end)
    }
}


class SizeBuilder : SpanBuilderContext {

    var width: DimensionValue? = null
    var height: DimensionValue? = null
}

class MarginBuilder : SpanBuilderContext {

    var start: DimensionValue? = null
    var top: DimensionValue? = null
    var bottom: DimensionValue? = null
    var end: DimensionValue? = null
}

class BulletSpanBuilder : SpanBuilderContext {

    var gapWidth: DimensionValue? = null
    var color: ColorSource? = null
    var radius: DimensionValue? = null
}

class QuoteSpanBuilder : SpanBuilderContext {

    var color: ColorSource? = null
    var stripeWidth: DimensionValue? = null
    var gapWidth: DimensionValue? = null
}

class TextAppearanceBuilder {

    @StyleableRes
    var colorListResId: Int? = null
}

fun SpanBuilderContext.colorFromString(hexColor: String): ColorSource {
    return ColorSource.FromString(color = hexColor)
}

fun SpanBuilderContext.colorFromInt(color: Int): ColorSource {
    return ColorSource.FromInt(color = color)
}

fun SpanBuilderContext.colorFromResource(@ColorRes colorResId: Int): ColorSource {
    return ColorSource.FromResources(colorResId = colorResId)
}


fun SpanBuilderContext.dimensionFromPx(@Dimension(unit = Dimension.PX) value: Int): DimensionValue {
    return DimensionValue.FromPx(value = value)
}

fun SpanBuilderContext.dimensionFromDp(@Dimension(unit = Dimension.DP) value: Int): DimensionValue {
    return DimensionValue.FromDp(value = value)
}

fun SpanBuilderContext.dimensionFromSp(@Dimension(unit = Dimension.SP) value: Float): DimensionValue {
    return DimensionValue.FromSp(value = value)
}

fun SpanBuilderContext.dimensionFromResource(@DimenRes dimenResId: Int): DimensionValue {
    return DimensionValue.FromResource(dimenResId = dimenResId)
}

fun SpanBuilderContext.imageFromDrawable(@DrawableRes drawableResId: Int): ImageSource {
    return ImageSource.FromDrawable(drawableResId = drawableResId)
}

fun SpanBuilderContext.imageFromAssets(fileName: String): ImageSource {
    return ImageSource.FromBitmap(bitmapSource = BitmapSource.FromAssets(fileName = fileName))
}

fun SpanBuilderContext.imageFromUri(uri: String): ImageSource {
    return ImageSource.FromBitmap(bitmapSource = BitmapSource.FromUri(uri = uri))
}

fun SpanBuilderContext.typefaceFromAssets(fileName: String): TypefaceSource {
    return TypefaceSource.FromAssets(fileName = fileName)
}

fun SpanBuilderContext.typefaceFromResources(
    @FontRes fontResId: Int,
    style: TypefaceStyle = TypefaceStyle.NORMAL
): TypefaceSource {
    return TypefaceSource.FromResources(fontResId = fontResId, style = style)
}

fun SpanBuilderContext.typefaceFromFamilyName(familyName: String): TypefaceSource {
    return TypefaceSource.FromFamilyName(familyName = familyName)
}

fun SpanBuilderContext.blurMaskFilterType(
    blurType: BlurType,
    @FloatRange(from = 0.0) radius: Float
): MaskFilterType {
    return MaskFilterType.Blur(blurType = blurType, radius = radius)
}