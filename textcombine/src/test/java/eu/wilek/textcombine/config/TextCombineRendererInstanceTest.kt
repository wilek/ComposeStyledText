package eu.wilek.textcombine.config

import android.content.Context
import eu.wilek.textcombine.TextCombine.StyleSpan
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
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.renderer.spannable.SpannableCreator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mockConstruction
import org.mockito.kotlin.mock
import kotlin.reflect.KClass


internal class TextCombineRendererInstanceTest {

    private val context: Context = mock()
    private val rendererInstance = TextCombineRendererInstance.withContext(context = context)

    @Test
    fun `should set custom absolute size span renderer`() {
        // given
        val absoluteSizeSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomAbsoluteSizeSpanRenderer(spanRenderer = absoluteSizeSpanRenderer)

        // then
        assertEquals(expected = absoluteSizeSpanRenderer, spanClass = AbsoluteSize::class)
    }

    @Test
    fun `should set custom background color span renderer`() {
        // given
        val backgroundColorSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomBackgroundColorSpanRenderer(spanRenderer = backgroundColorSpanRenderer)

        // then
        assertEquals(expected = backgroundColorSpanRenderer, spanClass = BackgroundColor::class)
    }

    @Test
    fun `should set custom clickable span renderer`() {
        // given
        val clickableSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomClickableSpanRenderer(spanRenderer = clickableSpanRenderer)

        // then
        assertEquals(expected = clickableSpanRenderer, spanClass = Clickable::class)
    }

    @Test
    fun `should set custom foreground color span renderer`() {
        // given
        val foregroundColorSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomForegroundColorSpanRenderer(spanRenderer = foregroundColorSpanRenderer)

        // then
        assertEquals(expected = foregroundColorSpanRenderer, spanClass = ForegroundColor::class)
    }

    @Test
    fun `should set custom image span renderer`() {
        // given
        val imageSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomImageSpanRenderer(spanRenderer = imageSpanRenderer)

        // then
        assertEquals(expected = imageSpanRenderer, spanClass = Image::class)
    }

    @Test
    fun `should set custom mask filter span renderer`() {
        // given
        val maskFilterSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomMaskFilterSpanRenderer(spanRenderer = maskFilterSpanRenderer)

        // then
        assertEquals(expected = maskFilterSpanRenderer, spanClass = MaskFilter::class)
    }

    @Test
    fun `should set custom relative size span renderer`() {
        // given
        val relativeSizeSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomRelativeSizeSpanRenderer(spanRenderer = relativeSizeSpanRenderer)

        // then
        assertEquals(expected = relativeSizeSpanRenderer, spanClass = RelativeSize::class)
    }

    @Test
    fun `should set custom scale x span renderer`() {
        // given
        val scaleXSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomScaleXSpanRenderer(spanRenderer = scaleXSpanRenderer)

        // then
        assertEquals(expected = scaleXSpanRenderer, spanClass = ScaleX::class)
    }

    @Test
    fun `should set custom strikethrough span renderer`() {
        // given
        val strikethroughSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomStrikethroughSpanRenderer(spanRenderer = strikethroughSpanRenderer)

        // then
        assertEquals(expected = strikethroughSpanRenderer, spanClass = Strikethrough::class)
    }

    @Test
    fun `should set custom style span renderer`() {
        // given
        val styleSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomStyleSpanRenderer(spanRenderer = styleSpanRenderer)

        // then
        assertEquals(expected = styleSpanRenderer, spanClass = Style::class)
    }

    @Test
    fun `should set custom subscript span renderer`() {
        // given
        val subscriptSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomSubscriptSpanRenderer(spanRenderer = subscriptSpanRenderer)

        // then
        assertEquals(expected = subscriptSpanRenderer, spanClass = Subscript::class)
    }

    @Test
    fun `should set custom superscript span renderer`() {
        // given
        val superscriptSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomSuperscriptSpanRenderer(spanRenderer = superscriptSpanRenderer)

        // then
        assertEquals(expected = superscriptSpanRenderer, spanClass = Superscript::class)
    }

    @Test
    fun `should set custom text appearance span renderer`() {
        // given
        val textAppearanceSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomTextAppearanceSpanRenderer(spanRenderer = textAppearanceSpanRenderer)

        // then
        assertEquals(expected = textAppearanceSpanRenderer, spanClass = TextAppearance::class)
    }

    @Test
    fun `should set custom typeface span renderer`() {
        // given
        val typefaceSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomTypefaceSpanRenderer(spanRenderer = typefaceSpanRenderer)

        // then
        assertEquals(expected = typefaceSpanRenderer, spanClass = Typeface::class)
    }

    @Test
    fun `should set custom underline span renderer`() {
        // given
        val underlineSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomUnderlineSpanRenderer(spanRenderer = underlineSpanRenderer)

        // then
        assertEquals(expected = underlineSpanRenderer, spanClass = Underline::class)
    }

    @Test
    fun `should set custom alignment span renderer`() {
        // given
        val alignmentSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomAlignmentSpanRenderer(spanRenderer = alignmentSpanRenderer)

        // then
        assertEquals(expected = alignmentSpanRenderer, spanClass = Alignment::class)
    }

    @Test
    fun `should set custom bullet span renderer`() {
        // given
        val bulletSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomBulletSpanRenderer(spanRenderer = bulletSpanRenderer)

        // then
        assertEquals(expected = bulletSpanRenderer, spanClass = Bullet::class)
    }

    @Test
    fun `should set custom leading image span renderer`() {
        // given
        val leadingImageSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomLeadingImageSpanRenderer(spanRenderer = leadingImageSpanRenderer)

        // then
        assertEquals(expected = leadingImageSpanRenderer, spanClass = LeadingImage::class)
    }

    @Test
    fun `should set custom leading margin span renderer`() {
        // given
        val leadingMarginSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomLeadingMarginSpanRenderer(spanRenderer = leadingMarginSpanRenderer)

        // then
        assertEquals(expected = leadingMarginSpanRenderer, spanClass = LeadingMargin::class)
    }

    @Test
    fun `should set custom line background span renderer`() {
        // given
        val lineBackgroundSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomLineBackgroundSpanRenderer(spanRenderer = lineBackgroundSpanRenderer)

        // then
        assertEquals(expected = lineBackgroundSpanRenderer, spanClass = LineBackground::class)
    }

    @Test
    fun `should set custom line height span renderer`() {
        // given
        val lineHeightSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomLineHeightSpanRenderer(spanRenderer = lineHeightSpanRenderer)

        // then
        assertEquals(expected = lineHeightSpanRenderer, spanClass = LineHeight::class)
    }

    @Test
    fun `should set custom quote span renderer`() {
        // given
        val quoteSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomQuoteSpanRenderer(spanRenderer = quoteSpanRenderer)

        // then
        assertEquals(expected = quoteSpanRenderer, spanClass = Quote::class)
    }

    @Test
    fun `should set custom tab stop renderer`() {
        // given
        val tabStopSpanRenderer: SpanRenderer = mock()

        // when
        rendererInstance.setCustomTabStopSpanRenderer(spanRenderer = tabStopSpanRenderer)

        // then
        assertEquals(expected = tabStopSpanRenderer, spanClass = TabStop::class)
    }

    private fun assertEquals(expected: SpanRenderer, spanClass: KClass<out StyleSpan>) {
        mockConstruction(SpannableCreator::class.java) { _, context ->
            assertEquals(expected, (context.arguments().first() as Map<*, *>)[spanClass])
        }.use {
            rendererInstance.get()
        }
    }
}
