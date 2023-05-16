package com.example.myapplication.ui.string.combine

import androidx.annotation.ColorRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

data class StringCombine(val texts: List<TextValue>) {

    data class TextValue(
        val source: TextSource,
        val formatArgs: List<TextValue> = emptyList(),
        val span: List<TextSpan> = emptyList()
    )

    sealed class TextSource {
        data class FromString(val text: String) : TextSource()
        data class FromStringResource(@StringRes val resourceId: Int) : TextSource()
        data class FromStringPluralResource(@PluralsRes val resourceId: Int, val count: Int) : TextSource()
    }

    sealed class TextSpan {
        sealed class TypefaceSpan : TextSpan() {
            object Bold : TypefaceSpan()
            object Italic : TypefaceSpan()
        }

        data class ForegroundColorSpan(val color: ColorSource) : TextSpan()
    }

    sealed class ColorSource {
        data class FromString(val color: String) : ColorSource()
        data class FromInt(val color: Int) : ColorSource()
        data class FromResources(@ColorRes val resourceId: Int) : ColorSource()
    }
}
