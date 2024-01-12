package com.example.myapplication.ui.string.combine

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import com.example.myapplication.ui.span.character.AbsoluteSizeSpanCreator
import com.example.myapplication.ui.span.character.BackgroundColorSpanCreator
import com.example.myapplication.ui.span.character.ClickableSpanCreator
import com.example.myapplication.ui.span.character.ForegroundColorSpanCreator
import com.example.myapplication.ui.span.character.ImageSpanCreator
import com.example.myapplication.ui.span.character.MaskFilterSpanCreator
import com.example.myapplication.ui.span.character.RelativeSizeSpanCreator
import com.example.myapplication.ui.span.character.ScaleXSpanCreator
import com.example.myapplication.ui.span.character.StrikethroughSpanCreator
import com.example.myapplication.ui.span.character.StyleSpanCreator
import com.example.myapplication.ui.span.character.SubscriptSpanCreator
import com.example.myapplication.ui.span.character.SuperscriptSpanCreator
import com.example.myapplication.ui.span.character.TextAppearanceSpanCreator
import com.example.myapplication.ui.span.character.TypefaceSpanCreator
import com.example.myapplication.ui.span.character.UnderlineSpanCreator
import com.example.myapplication.ui.span.paragraph.AlignmentSpanCreator
import com.example.myapplication.ui.span.paragraph.BulletSpanCreator
import com.example.myapplication.ui.span.paragraph.LeadingImageSpanCreator
import com.example.myapplication.ui.span.paragraph.LeadingMarginSpanCreator
import com.example.myapplication.ui.span.paragraph.LineBackgroundSpanCreator
import com.example.myapplication.ui.span.paragraph.LineHeightSpanCreator
import com.example.myapplication.ui.span.paragraph.QuoteSpanCreator
import com.example.myapplication.ui.span.paragraph.TabStopSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.ParagraphStyle
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.TextCombine.TextValue


class TextCombineRenderer(
    private val context: Context,
    private val absoluteSizeSpanCreator: AbsoluteSizeSpanCreator = AbsoluteSizeSpanCreator(),
    private val backgroundColorSpanCreator: BackgroundColorSpanCreator = BackgroundColorSpanCreator(),
    private val clickableSpanCreator: ClickableSpanCreator = ClickableSpanCreator(),
    private val foregroundColorSpanCreator: ForegroundColorSpanCreator = ForegroundColorSpanCreator(),
    private val imageSpanCreator: ImageSpanCreator = ImageSpanCreator(),
    private val maskFilterSpanCreator: MaskFilterSpanCreator = MaskFilterSpanCreator(),
    private val relativeSizeSpanCreator: RelativeSizeSpanCreator = RelativeSizeSpanCreator(),
    private val scaleXSpanCreator: ScaleXSpanCreator = ScaleXSpanCreator(),
    private val strikethroughSpanCreator: StrikethroughSpanCreator = StrikethroughSpanCreator(),
    private val styleSpanCreator: StyleSpanCreator = StyleSpanCreator(),
    private val subscriptSpanCreator: SubscriptSpanCreator = SubscriptSpanCreator(),
    private val superscriptSpanCreator: SuperscriptSpanCreator = SuperscriptSpanCreator(),
    private val textAppearanceSpanCreator: TextAppearanceSpanCreator = TextAppearanceSpanCreator(),
    private val typefaceSpanCreator: TypefaceSpanCreator = TypefaceSpanCreator(),
    private val underlineSpanCreator: UnderlineSpanCreator = UnderlineSpanCreator(),
    private val alignmentSpanCreator: AlignmentSpanCreator = AlignmentSpanCreator(),
    private val bulletSpanCreator: BulletSpanCreator = BulletSpanCreator(),
    private val leadingImageSpanCreator: LeadingImageSpanCreator = LeadingImageSpanCreator(),
    private val leadingMarginSpanCreator: LeadingMarginSpanCreator = LeadingMarginSpanCreator(),
    private val lineBackgroundSpanCreator: LineBackgroundSpanCreator = LineBackgroundSpanCreator(),
    private val lineHeightSpanCreator: LineHeightSpanCreator = LineHeightSpanCreator(),
    private val quoteSpanCreator: QuoteSpanCreator = QuoteSpanCreator(),
    private val tabStopSpanCreator: TabStopSpanCreator = TabStopSpanCreator()
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
        is CharacterStyle.AbsoluteSize -> absoluteSizeSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.BackgroundColor -> backgroundColorSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Clickable -> clickableSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.ForegroundColor -> foregroundColorSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Image -> imageSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.MaskFilter -> maskFilterSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.RelativeSize -> relativeSizeSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.ScaleX -> scaleXSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Strikethrough -> strikethroughSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Style -> styleSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Subscript -> subscriptSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Superscript -> superscriptSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.TextAppearance -> textAppearanceSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Typeface -> typefaceSpanCreator.createSpan(context = context, styleSpan = this)
        is CharacterStyle.Underline -> underlineSpanCreator.createSpan(context = context, styleSpan = this)
    }

    private fun ParagraphStyle.resolve() = when (this) {
        is ParagraphStyle.Alignment -> alignmentSpanCreator.createSpan(context = context, styleSpan = this)
        is ParagraphStyle.Bullet -> bulletSpanCreator.createSpan(context = context, styleSpan = this)
        is ParagraphStyle.LeadingImage -> leadingImageSpanCreator.createSpan(context = context, styleSpan = this)
        is ParagraphStyle.LeadingMargin -> leadingMarginSpanCreator.createSpan(context = context, styleSpan = this)
        is ParagraphStyle.LineBackground -> lineBackgroundSpanCreator.createSpan(context = context, styleSpan = this)
        is ParagraphStyle.LineHeight -> lineHeightSpanCreator.createSpan(context = context, styleSpan = this)
        is ParagraphStyle.Quote -> quoteSpanCreator.createSpan(context = context, styleSpan = this)
        is ParagraphStyle.TabStop -> tabStopSpanCreator.createSpan(context = context, styleSpan = this)
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
