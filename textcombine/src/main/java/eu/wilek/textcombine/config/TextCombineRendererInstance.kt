package eu.wilek.textcombine.config

import android.content.Context
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.BackgroundColor
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Clickable
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.ForegroundColor
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
import eu.wilek.textcombine.renderer.TextCombineRenderer
import eu.wilek.textcombine.renderer.TextCombineRendererImpl
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.renderer.span.character.AbsoluteSizeSpanRenderer
import eu.wilek.textcombine.renderer.span.character.BackgroundColorSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ClickableSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ForegroundColorSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ImageSpanRenderer
import eu.wilek.textcombine.renderer.span.character.MaskFilterSpanRenderer
import eu.wilek.textcombine.renderer.span.character.RelativeSizeSpanRenderer
import eu.wilek.textcombine.renderer.span.character.ScaleXSpanRenderer
import eu.wilek.textcombine.renderer.span.character.StrikethroughSpanRenderer
import eu.wilek.textcombine.renderer.span.character.StyleSpanRenderer
import eu.wilek.textcombine.renderer.span.character.SubscriptSpanRenderer
import eu.wilek.textcombine.renderer.span.character.SuperscriptSpanRenderer
import eu.wilek.textcombine.renderer.span.character.TextAppearanceSpanRenderer
import eu.wilek.textcombine.renderer.span.character.TypefaceSpanRenderer
import eu.wilek.textcombine.renderer.span.character.UnderlineSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.AlignmentSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.BulletSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LeadingImageSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LeadingMarginSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LineBackgroundSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.LineHeightSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.QuoteSpanRenderer
import eu.wilek.textcombine.renderer.span.paragraph.TabStopSpanRenderer
import eu.wilek.textcombine.renderer.spannable.SpannableCreator

class TextCombineRendererInstance private constructor(private val context: Context) {

    private val defaultSpanRenderers = mutableMapOf(
        AbsoluteSize::class to AbsoluteSizeSpanRenderer(),
        BackgroundColor::class to BackgroundColorSpanRenderer(),
        Clickable::class to ClickableSpanRenderer(),
        ForegroundColor::class to ForegroundColorSpanRenderer(),
        Image::class to ImageSpanRenderer(),
        MaskFilter::class to MaskFilterSpanRenderer(),
        RelativeSize::class to RelativeSizeSpanRenderer(),
        ScaleX::class to ScaleXSpanRenderer(),
        Strikethrough::class to StrikethroughSpanRenderer(),
        Style::class to StyleSpanRenderer(),
        Subscript::class to SubscriptSpanRenderer(),
        Superscript::class to SuperscriptSpanRenderer(),
        TextAppearance::class to TextAppearanceSpanRenderer(),
        Typeface::class to TypefaceSpanRenderer(),
        Underline::class to UnderlineSpanRenderer(),
        Alignment::class to AlignmentSpanRenderer(),
        Bullet::class to BulletSpanRenderer(),
        LeadingImage::class to LeadingImageSpanRenderer(),
        LeadingMargin::class to LeadingMarginSpanRenderer(),
        LineBackground::class to LineBackgroundSpanRenderer(),
        LineHeight::class to LineHeightSpanRenderer(),
        Quote::class to QuoteSpanRenderer(),
        TabStop::class to TabStopSpanRenderer()
    )

    fun setCustomAbsoluteSizeSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[AbsoluteSize::class] = spanRenderer
    }

    fun setCustomBackgroundColorSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[BackgroundColor::class] = spanRenderer
    }

    fun setCustomClickableSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Clickable::class] = spanRenderer
    }

    fun setCustomForegroundColorSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[ForegroundColor::class] = spanRenderer
    }

    fun setCustomImageSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Image::class] = spanRenderer
    }

    fun setCustomMaskFilterSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[MaskFilter::class] = spanRenderer
    }

    fun setCustomRelativeSizeSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[RelativeSize::class] = spanRenderer
    }

    fun setCustomScaleXSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[ScaleX::class] = spanRenderer
    }

    fun setCustomStrikethroughSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Strikethrough::class] = spanRenderer
    }

    fun setCustomStyleSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Style::class] = spanRenderer
    }

    fun setCustomSubscriptSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Subscript::class] = spanRenderer
    }

    fun setCustomSuperscriptSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Superscript::class] = spanRenderer
    }

    fun setCustomTextAppearanceSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[TextAppearance::class] = spanRenderer
    }

    fun setCustomTypefaceSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Typeface::class] = spanRenderer
    }

    fun setCustomUnderlineSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Underline::class] = spanRenderer
    }

    fun setCustomAlignmentSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Alignment::class] = spanRenderer
    }

    fun setCustomBulletSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Bullet::class] = spanRenderer
    }

    fun setCustomLeadingImageSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[LeadingImage::class] = spanRenderer
    }

    fun setCustomLeadingMarginSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[LeadingMargin::class] = spanRenderer
    }

    fun setCustomLineBackgroundSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[LineBackground::class] = spanRenderer
    }

    fun setCustomLineHeightSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[LineHeight::class] = spanRenderer
    }

    fun setCustomQuoteSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[Quote::class] = spanRenderer
    }

    fun setCustomTabStopSpanRenderer(spanRenderer: SpanRenderer) {
        defaultSpanRenderers[TabStop::class] = spanRenderer
    }

    fun get(): TextCombineRenderer {
        return TextCombineRendererImpl(
            context = context,
            spanCreator = SpannableCreator(spanRenderers = defaultSpanRenderers)
        )
    }

    companion object {

        fun withContext(context: Context) = TextCombineRendererInstance(context = context)
    }
}
