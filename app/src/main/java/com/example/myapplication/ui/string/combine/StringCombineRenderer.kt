package com.example.myapplication.ui.string.combine

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.TextUtils
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.*
import com.example.myapplication.ui.string.combine.StringCombine.TextValue

class StringCombineRenderer(private val context: Context) {

    data class FormatResult(val text: CharSequence, val spanPositions: List<SpanPosition>) {
        data class SpanPosition(val start: Int, val end: Int, val spans: List<StringCombine.TextSpan>)
    }

    fun render(string: StringCombine): CharSequence {
        return render(string.texts)
    }

    private fun render(texts: List<TextValue>): CharSequence {
        val textsFormatted = texts.scan(FormatResult(text = "", spanPositions = emptyList())) { acc, textValue ->
            readTextValue(textValue, acc.text.length)
        }
        val joinedText = TextUtils.concat(*textsFormatted.map { it.text }.toTypedArray())
        return setSpans(joinedText, textsFormatted.flatMap { it.spanPositions })
    }

    private fun readTextValue(textValue: TextValue, previousTextLength: Int): FormatResult {
        return when (val source = textValue.source) {
            is FromString -> source.readString(formatArgs = textValue.formatArgs, textValue.span, previousTextLength)
            is FromStringResource -> source.readString(formatArgs = textValue.formatArgs, textValue.span, previousTextLength)
            is FromStringPluralResource -> source.readString(formatArgs = textValue.formatArgs, textValue.span, previousTextLength)
        }
    }

    private fun FromString.readString(formatArgs: List<TextValue>, spans: List<StringCombine.TextSpan>, previousTextLength: Int): FormatResult {
        return formatArguments(text, formatArgs, spans, previousTextLength)
    }

    private fun FromStringResource.readString(formatArgs: List<TextValue>, spans: List<StringCombine.TextSpan>, previousTextLength: Int): FormatResult {
        return formatArguments(context.getString(resourceId), formatArgs, spans, previousTextLength)
    }

    private fun FromStringPluralResource.readString(formatArgs: List<TextValue>, spans: List<StringCombine.TextSpan>, previousTextLength: Int): FormatResult {
        return formatArguments(context.resources.getQuantityString(resourceId, count), formatArgs, spans, previousTextLength)
    }

    private fun formatArguments(text: CharSequence, formatArgs: List<TextValue>, spans: List<StringCombine.TextSpan>, previousTextLength: Int): FormatResult {
        val argumentResult = mutableListOf<FormatResult.SpanPosition>()
        val placeholders = STRING_FORMAT_REGEXP.findAll(text).toList()
        var formattedString = text
        var lastPlaceholderOffset = 0
        placeholders.forEachIndexed { index, matchResult ->
            val fixedIndex = DIGIT_REGEXP.find(matchResult.value)?.value?.toInt()?.dec() ?: index
            val formatArgument = readTextValue(formatArgs[fixedIndex], previousTextLength)
            formattedString = formattedString.replaceRange(
                startIndex = matchResult.range.first + lastPlaceholderOffset,
                endIndex = matchResult.range.last + lastPlaceholderOffset + 1,
                replacement = formatArgument.text
            )

            argumentResult.addAll(formatArgument.spanPositions.map {
                it.copy(
                    start = it.start + matchResult.range.first + lastPlaceholderOffset,
                    end = it.end + matchResult.range.first + lastPlaceholderOffset
                )
            })

            lastPlaceholderOffset += when {
                formatArgument.text.length >= matchResult.range.count() -> -(matchResult.range.count() - formatArgument.text.length)
                else -> formatArgument.text.length - matchResult.range.count()
            }
        }

        argumentResult.add(
            FormatResult.SpanPosition(
                start = previousTextLength,
                end = formattedString.length + previousTextLength,
                spans = spans
            )
        )

        return FormatResult(text = formattedString, spanPositions = argumentResult)
    }

    private fun setSpans(text: CharSequence, spanPositions: List<FormatResult.SpanPosition>): CharSequence {
        val spannable = SpannableString(text)

        spanPositions.forEach { spanPosition ->
            spanPosition.spans.forEach {
                spannable.setSpan(it.resolve(), spanPosition.start, spanPosition.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }

        return spannable
    }

    private fun StringCombine.TextSpan.resolve() = when (this) {
        StringCombine.TextSpan.TypefaceSpan.Bold -> StyleSpan(Typeface.BOLD)
        StringCombine.TextSpan.TypefaceSpan.Italic -> StyleSpan(Typeface.ITALIC)
        is StringCombine.TextSpan.ForegroundColorSpan -> ForegroundColorSpan(color.resolve())
    }

    private fun StringCombine.ColorSource.resolve() = when (this) {
        is StringCombine.ColorSource.FromString -> Color.parseColor(color)
        is StringCombine.ColorSource.FromInt -> color
        is StringCombine.ColorSource.FromResources -> context.getColor(resourceId)
    }

    private companion object {
        private val DIGIT_REGEXP = "\\d+".toRegex()
        private val STRING_FORMAT_REGEXP = "%s|%\\d+\\$?s".toRegex()
    }
}
