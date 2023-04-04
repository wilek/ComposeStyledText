package com.example.myapplication.ui.string.combine

import android.content.Context
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.FromString
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.FromStringPluralResource
import com.example.myapplication.ui.string.combine.StringCombine.TextSource.FromStringResource
import com.example.myapplication.ui.string.combine.StringCombine.TextValue

class StringCombineRenderer(private val context: Context) {

    fun render(string: StringCombine): CharSequence {
        return render(string.texts)
    }

    private fun render(texts: List<TextValue>): CharSequence {
        return texts.joinToString("") { textValue -> readTextValue(textValue = textValue) }
    }

    private fun readTextValue(textValue: TextValue): CharSequence {
        return when (val source = textValue.source) {
            is FromString -> source.readString(formatArgs = textValue.formatArgs)
            is FromStringResource -> source.readString(formatArgs = textValue.formatArgs)
            is FromStringPluralResource -> source.readString(formatArgs = textValue.formatArgs)
        }
    }

    private fun FromString.readString(formatArgs: List<TextValue>): CharSequence {
        return text.format(*format(formatArgs))
    }

    private fun FromStringResource.readString(formatArgs: List<TextValue>): CharSequence {
        return context.getString(resourceId, *format(formatArgs))
    }

    private fun FromStringPluralResource.readString(formatArgs: List<TextValue>): CharSequence {
        return context.resources.getQuantityString(resourceId, count, *format(formatArgs))
    }

    private fun format(texts: List<TextValue>): Array<CharSequence> {
        return texts.map { textValue -> readTextValue(textValue = textValue) }.toTypedArray()
    }
}
