package com.example.myapplication.ui.string.combine

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

data class StringCombine(val texts: List<TextValue>) {

    data class TextValue(val source: TextSource, val formatArgs: List<TextValue> = emptyList())

    sealed class TextSource {
        data class FromString(val text: String) : TextSource()
        data class FromStringResource(@StringRes val resourceId: Int) : TextSource()
        data class FromStringPluralResource(@PluralsRes val resourceId: Int, val count: Int) : TextSource()
    }
}
