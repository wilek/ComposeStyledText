package com.example.myapplication.ui.string.combine

import androidx.annotation.ColorRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.TextCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.TextCombine.TextValue
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan

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
            span = spans
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
    override var source = FromStringResource(resourceId = resourceId)
    override val spans = mutableListOf<StyleSpan>()

    fun setSpans(builder: SpanBuilder.() -> Unit = {}) {
        spans.addAll(SpanBuilder().apply(builder).build())
    }
}

class StringPluralResourceTextValueBuilder(@PluralsRes resourceId: Int, count: Int) : TextValueBuilder() {
    override var source = FromStringPluralResource(resourceId = resourceId, count = count)
    override val spans = mutableListOf<StyleSpan>()

    fun setSpans(builder: SpanBuilder.() -> Unit = {}) {
        spans.addAll(SpanBuilder().apply(builder).build())
    }
}

class SpanBuilder {
    private val spans = mutableListOf<StyleSpan>()

    fun bold() {
        //spans.add(TextSpan.CharacterStyle.Bold)
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

class ColorBuilder {

    private lateinit var colorSource: TextCombine.ColorSource

    fun fromString(hexColor: String) {
        colorSource = TextCombine.ColorSource.FromString(color = hexColor)
    }

    fun fromInt(color: Int) {
        colorSource = TextCombine.ColorSource.FromInt(color = color)
    }

    fun fromResource(@ColorRes resourceId: Int) {
        colorSource = TextCombine.ColorSource.FromResources(resourceId = resourceId)
    }

    fun build(): TextCombine.ColorSource {
        return colorSource
    }
}

