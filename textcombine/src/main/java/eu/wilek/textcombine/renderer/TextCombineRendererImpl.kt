package eu.wilek.textcombine.renderer

import android.content.Context
import eu.wilek.textcombine.TextCombine
import eu.wilek.textcombine.TextCombine.TextSource.FromString
import eu.wilek.textcombine.TextCombine.TextSource.FromStringPluralResource
import eu.wilek.textcombine.TextCombine.TextSource.FromStringResource
import eu.wilek.textcombine.TextCombine.TextValue
import eu.wilek.textcombine.renderer.spannable.SpannableCreator

internal class TextCombineRendererImpl(
    private val context: Context,
    private val spanCreator: SpannableCreator
) : TextCombineRenderer {
    private data class Phrase(val text: CharSequence, val spans: List<PhraseSpan>)

    data class PhraseSpan(val start: Int, val end: Int, val spans: List<TextCombine.StyleSpan>)

    override fun render(textCombine: TextCombine): CharSequence {
        var diffSpan = 0
        return textCombine.texts
            .map { text -> readTextValue(textValue = text) }
            .map { phrase ->
                val updatedPhrase = phrase.moveSpansBy(size = diffSpan)
                diffSpan += phrase.text.length
                updatedPhrase
            }
            .mergePhrases()
            .appendMainSpans(spans = textCombine.spans)
            .let { createSpannedText(text = it.text, spans = it.spans) }

    }

    private fun readTextValue(textValue: TextValue) = when (val source = textValue.text) {
        is FromString -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.spans)
        is FromStringResource -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.spans)
        is FromStringPluralResource -> source.readString(formatArgs = textValue.formatArgs, spans = textValue.spans)
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
        return formatText(text = context.getString(stringResId), formatArgs = formatArgs, spans = spans)
    }

    private fun FromStringPluralResource.readString(
        formatArgs: List<TextValue>,
        spans: List<TextCombine.StyleSpan>
    ): Phrase {
        return formatText(text = context.resources.getQuantityString(pluralResId, count), formatArgs = formatArgs, spans = spans)
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
        spans: List<PhraseSpan>,
    ): CharSequence {
        return spanCreator.createSpan(context = context, text = text, textSpans = spans)
    }

    private fun Phrase.moveSpansBy(size: Int) = copy(
        spans = spans.map { span -> span.copy(start = span.start + size, end = span.end + size) }
    )

    private fun List<Phrase>.mergePhrases() = Phrase(
        text = joinToString(separator = "") { it.text },
        spans = flatMap { text -> text.spans }
    )

    private fun Phrase.appendMainSpans(spans: List<TextCombine.StyleSpan>) = when {
        spans.isEmpty() -> this
        else -> copy(spans = listOf(PhraseSpan(start = 0, end = text.length, spans = spans)) + this.spans)
    }

    private companion object {
        val STRING_FORMAT_REGEXP = "%s|%\\d+\\$?s".toRegex()
        val DIGIT_REGEXP = "\\d+".toRegex()
    }
}
