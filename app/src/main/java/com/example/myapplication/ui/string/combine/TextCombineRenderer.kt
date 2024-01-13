package com.example.myapplication.ui.string.combine

import android.content.Context
import com.example.myapplication.ui.span.creator.DefaultSpannableCreator
import com.example.myapplication.ui.span.creator.SpannableCreator
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.TextCombine.TextValue

class TextCombineRenderer(
    private val context: Context,
    private val spanCreator: SpannableCreator = DefaultSpannableCreator()
) {
    private data class Phrase(val text: CharSequence, val spans: List<PhraseSpan>)

    data class PhraseSpan(val start: Int, val end: Int, val spans: List<TextCombine.StyleSpan>)

    fun render(textCombine: TextCombine): CharSequence {
        var diffSpan = 0
        return textCombine.texts
            .map { text -> readTextValue(textValue = text) }
            .map { phrase ->
                val updatedPhrase = phrase.moveSpansBy(size = diffSpan)
                diffSpan += phrase.text.length
                updatedPhrase
            }
            .create()
    }

    private fun readTextValue(textValue: TextValue) = when (val source = textValue.source) {
        is FromString -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.span)
        is FromStringResource -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.span)
        is FromStringPluralResource -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.span)
    }

    private fun FromString.readString(
        formatArgs: List<TextValue>,
        spans: List<TextCombine.StyleSpan>
    ): Phrase {
        return formatText(text = text, formatArgs = formatArgs, spans = spans)
    }

    private fun FromStringResource.readString(
        formatArgs: List<TextValue>,
        spans: List<TextCombine.StyleSpan>
    ): Phrase {
        return formatText(text = context.getString(resourceId), formatArgs = formatArgs, spans = spans)
    }

    private fun FromStringPluralResource.readString(
        formatArgs: List<TextValue>,
        spans: List<TextCombine.StyleSpan>
    ): Phrase {
        return formatText(text = context.resources.getQuantityString(resourceId, count), formatArgs = formatArgs, spans = spans)
    }

    private fun formatText(
        text: CharSequence,
        formatArgs: List<TextValue>,
        spans: List<TextCombine.StyleSpan>
    ): Phrase {
        val formatArgsPlaceholders = STRING_FORMAT_REGEXP.findAll(input = text).toList()
        var formattedText = text
        val formattedTextSpans = mutableListOf<PhraseSpan>()
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

        return Phrase(
            text = formattedText,
            spans = spans.takeIf { it.isNotEmpty() }?.let {
                listOf(
                    PhraseSpan(
                        start = 0,
                        end = formattedText.length,
                        spans = it
                    )
                ) + formattedTextSpans
            } ?: formattedTextSpans
        )
    }

    private fun createSpannedText(
        text: CharSequence,
        textSpans: List<PhraseSpan>
    ): CharSequence {
        return spanCreator.createSpan(context = context, text = text, textSpans = textSpans)
    }

    private fun Phrase.moveSpansBy(size: Int) = copy(
        spans = spans.map { span -> span.copy(start = span.start + size, end = span.end + size) }
    )

    private fun List<Phrase>.create() = createSpannedText(
        text = joinToString(separator = "") { it.text },
        textSpans = flatMap { text -> text.spans }
    )

    private companion object {
        val STRING_FORMAT_REGEXP = "%s|%\\d+\\$?s".toRegex()
        val DIGIT_REGEXP = "\\d+".toRegex()
    }
}
