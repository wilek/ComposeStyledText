package com.example.myapplication.ui.string.combine

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.StringCombine.TextValue

fun stringCombine(builder: StringCombineBuilder.() -> Unit): StringCombine {
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

    fun build(): StringCombine {
        return StringCombine(texts = texts)
    }
}

abstract class TextValueBuilder {
    protected abstract val source: StringCombine.TextSource
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
            formatArgs = formatArgs
        )
    }
}

class StringTextValueBuilder(text: String) : TextValueBuilder() {
    override val source = FromString(text = text)
}

class StringResourceTextValueBuilder(@StringRes resourceId: Int) : TextValueBuilder() {
    override var source = FromStringResource(resourceId = resourceId)
}

class StringPluralResourceTextValueBuilder(@PluralsRes resourceId: Int, count: Int) : TextValueBuilder() {
    override var source = FromStringPluralResource(resourceId = resourceId, count = count)
}
