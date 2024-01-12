package com.example.myapplication.ui.string.combine

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import com.example.myapplication.ui.span.renderer.character.AbsoluteSizeSpanRenderer
import com.example.myapplication.ui.span.renderer.character.BackgroundColorSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ClickableSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ForegroundColorSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ImageSpanRenderer
import com.example.myapplication.ui.span.renderer.character.MaskFilterSpanRenderer
import com.example.myapplication.ui.span.renderer.character.RelativeSizeSpanRenderer
import com.example.myapplication.ui.span.renderer.character.ScaleXSpanRenderer
import com.example.myapplication.ui.span.renderer.character.StrikethroughSpanRenderer
import com.example.myapplication.ui.span.renderer.character.StyleSpanRenderer
import com.example.myapplication.ui.span.renderer.character.SubscriptSpanRenderer
import com.example.myapplication.ui.span.renderer.character.SuperscriptSpanRenderer
import com.example.myapplication.ui.span.renderer.character.TextAppearanceSpanRenderer
import com.example.myapplication.ui.span.renderer.character.TypefaceSpanRenderer
import com.example.myapplication.ui.span.renderer.character.UnderlineSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.AlignmentSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.BulletSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LeadingImageSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LeadingMarginSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LineBackgroundSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.LineHeightSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.QuoteSpanRenderer
import com.example.myapplication.ui.span.renderer.paragraph.TabStopSpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.TextCombine.TextValue


class TextCombineRenderer(
    private val context: Context,
    private val absoluteSizeSpanCreator: AbsoluteSizeSpanRenderer = AbsoluteSizeSpanRenderer(),
    private val backgroundColorSpanCreator: BackgroundColorSpanRenderer = BackgroundColorSpanRenderer(),
    private val clickableSpanCreator: ClickableSpanRenderer = ClickableSpanRenderer(),
    private val foregroundColorSpanCreator: ForegroundColorSpanRenderer = ForegroundColorSpanRenderer(),
    private val imageSpanCreator: ImageSpanRenderer = ImageSpanRenderer(),
    private val maskFilterSpanCreator: MaskFilterSpanRenderer = MaskFilterSpanRenderer(),
    private val relativeSizeSpanCreator: RelativeSizeSpanRenderer = RelativeSizeSpanRenderer(),
    private val scaleXSpanCreator: ScaleXSpanRenderer = ScaleXSpanRenderer(),
    private val strikethroughSpanCreator: StrikethroughSpanRenderer = StrikethroughSpanRenderer(),
    private val styleSpanCreator: StyleSpanRenderer = StyleSpanRenderer(),
    private val subscriptSpanCreator: SubscriptSpanRenderer = SubscriptSpanRenderer(),
    private val superscriptSpanCreator: SuperscriptSpanRenderer = SuperscriptSpanRenderer(),
    private val textAppearanceSpanCreator: TextAppearanceSpanRenderer = TextAppearanceSpanRenderer(),
    private val typefaceSpanCreator: TypefaceSpanRenderer = TypefaceSpanRenderer(),
    private val underlineSpanCreator: UnderlineSpanRenderer = UnderlineSpanRenderer(),
    private val alignmentSpanCreator: AlignmentSpanRenderer = AlignmentSpanRenderer(),
    private val bulletSpanCreator: BulletSpanRenderer = BulletSpanRenderer(),
    private val leadingImageSpanCreator: LeadingImageSpanRenderer = LeadingImageSpanRenderer(),
    private val leadingMarginSpanCreator: LeadingMarginSpanRenderer = LeadingMarginSpanRenderer(),
    private val lineBackgroundSpanCreator: LineBackgroundSpanRenderer = LineBackgroundSpanRenderer(),
    private val lineHeightSpanCreator: LineHeightSpanRenderer = LineHeightSpanRenderer(),
    private val quoteSpanCreator: QuoteSpanRenderer = QuoteSpanRenderer(),
    private val tabStopSpanCreator: TabStopSpanRenderer = TabStopSpanRenderer()
) {

    private data class Text(val text: CharSequence, val spans: List<Span>) {

        data class Span(val start: Int, val end: Int, val spans: List<TextCombine.StyleSpan>)

        companion object {
            val EMPTY = Text(text = "", spans = emptyList())
        }
    }

    fun render(textCombine: TextCombine): CharSequence {
        return textCombine.texts
            .map { text -> readTextValue(textValue = text) }
            .runningFold(Text.EMPTY) { acc, text -> text.moveSpansBy(size = acc.text.length) }
            .create()
    }

    private fun readTextValue(textValue: TextValue) = when (val source = textValue.source) {
        is FromString -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.span)
        is FromStringResource -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.span)
        is FromStringPluralResource -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.span)
    }

    private fun FromString.readString(formatArgs: List<TextValue>, spans: List<TextCombine.StyleSpan>): Text {
        return formatText(text = text, formatArgs = formatArgs, spans = spans)
    }

    private fun FromStringResource.readString(formatArgs: List<TextValue>, spans: List<TextCombine.StyleSpan>): Text {
        return formatText(text = context.getString(resourceId), formatArgs = formatArgs, spans = spans)
    }

    private fun FromStringPluralResource.readString(formatArgs: List<TextValue>, spans: List<TextCombine.StyleSpan>): Text {
        return formatText(text = context.resources.getQuantityString(resourceId, count), formatArgs = formatArgs, spans = spans)
    }

    private fun formatText(text: CharSequence, formatArgs: List<TextValue>, spans: List<TextCombine.StyleSpan>): Text {
        val formatArgsPlaceholders = STRING_FORMAT_REGEXP.findAll(input = text).toList()
        var formattedText = text
        val formattedTextSpans = mutableListOf<Text.Span>()
        var placeholderDiffCount = 0

        formatArgsPlaceholders.forEachIndexed { index, matchResult ->
            val argumentIndex = DIGIT_REGEXP.find(input = matchResult.value)?.value?.toInt()?.dec() ?: index
            val formatArgument = readTextValue(textValue = formatArgs[argumentIndex])
            formattedText = formattedText.replaceRange(
                startIndex = matchResult.range.first + placeholderDiffCount,
                endIndex = matchResult.range.last + placeholderDiffCount.inc(),
                replacement = formatArgument.text
            )
            formattedTextSpans.addAll(elements = formatArgument.spans.map { span ->
                span.copy(
                    start = span.start + matchResult.range.first + placeholderDiffCount,
                    end = span.end + matchResult.range.first + placeholderDiffCount
                )
            })
            placeholderDiffCount += formatArgument.text.count() - matchResult.range.count()
        }

        return Text(
            text = formattedText,
            spans = listOf(Text.Span(start = 0, end = formattedText.length, spans = spans)) + formattedTextSpans
        )
    }

    private fun createSpannedText(text: CharSequence, textSpans: List<Text.Span>): CharSequence {
        val spannable = SpannableString(text)

        textSpans.forEach { textSpan ->
            textSpan.spans.forEach { span ->
                spannable.setSpan(span.resolve(), textSpan.start, textSpan.end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            }
        }

        return spannable
    }

    private fun TextCombine.StyleSpan.resolve() = when (this) {
        is CharacterStyle -> resolve()
        is ParagraphStyle -> resolve()
    }

    private fun CharacterStyle.resolve() = when (this) {
        is CharacterStyle.AbsoluteSize -> absoluteSizeSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.BackgroundColor -> backgroundColorSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Clickable -> clickableSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.ForegroundColor -> foregroundColorSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Image -> imageSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.MaskFilter -> maskFilterSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.RelativeSize -> relativeSizeSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.ScaleX -> scaleXSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Strikethrough -> strikethroughSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Style -> styleSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Subscript -> subscriptSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Superscript -> superscriptSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.TextAppearance -> textAppearanceSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Typeface -> typefaceSpanCreator.renderSpan(context = context, styleSpan = this)
        is CharacterStyle.Underline -> underlineSpanCreator.renderSpan(context = context, styleSpan = this)
    }

    private fun ParagraphStyle.resolve() = when (this) {
        is ParagraphStyle.Alignment -> alignmentSpanCreator.renderSpan(context = context, styleSpan = this)
        is ParagraphStyle.Bullet -> bulletSpanCreator.renderSpan(context = context, styleSpan = this)
        is ParagraphStyle.LeadingImage -> leadingImageSpanCreator.renderSpan(context = context, styleSpan = this)
        is ParagraphStyle.LeadingMargin -> leadingMarginSpanCreator.renderSpan(context = context, styleSpan = this)
        is ParagraphStyle.LineBackground -> lineBackgroundSpanCreator.renderSpan(context = context, styleSpan = this)
        is ParagraphStyle.LineHeight -> lineHeightSpanCreator.renderSpan(context = context, styleSpan = this)
        is ParagraphStyle.Quote -> quoteSpanCreator.renderSpan(context = context, styleSpan = this)
        is ParagraphStyle.TabStop -> tabStopSpanCreator.renderSpan(context = context, styleSpan = this)
    }

    private fun Text.   moveSpansBy(size: Int) = copy(
        spans = spans.map { span -> span.copy(start = span.start + size, end = span.end + size) }
    )

    private fun List<Text>.create() = createSpannedText(
        text = joinToString(separator = "") { it.text },
        textSpans = flatMap { text -> text.spans }
    )

    private companion object {
        val DIGIT_REGEXP = "\\d+".toRegex()
        val STRING_FORMAT_REGEXP = "%s|%\\d+\\$?s".toRegex()
    }
}
