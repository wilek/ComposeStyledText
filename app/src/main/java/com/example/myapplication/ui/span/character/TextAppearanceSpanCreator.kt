package com.example.myapplication.ui.span.character

import android.content.Context
import android.content.res.ColorStateList
import android.text.style.TextAppearanceSpan
import com.example.myapplication.ui.span.TextCombineSpanCreator
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.TextAppearance
import com.example.myapplication.ui.string.combine.TextCombine.TextAppearanceType
import com.example.myapplication.ui.util.toColor
import com.example.myapplication.ui.util.toPx
import com.example.myapplication.ui.util.toStyle

open class TextAppearanceSpanCreator : TextCombineSpanCreator<TextAppearance> {
    override fun createSpan(context: Context, styleSpan: TextAppearance): Any {
        return when (val type = styleSpan.type) {
            is TextAppearanceType.FromResources -> TextAppearanceSpan(context, type.appearanceResId, type.colorListResId)
            is TextAppearanceType.FromSpecified -> type.toTextAppearanceSpanFromSpecified(context = context)
        }
    }

    private fun TextAppearanceType.FromSpecified.toTextAppearanceSpanFromSpecified(context: Context) = TextAppearanceSpan(
        typefaceFamily,
        typefaceStyle.toStyle(),
        textSize.toPx(context = context),
        ColorStateList(
            textColor.map { it.stateResIds }.toTypedArray(),
            textColor.map { it.color.toColor(context = context) }.toIntArray()
        ),
        ColorStateList(
            textLinkColor.map { it.stateResIds }.toTypedArray(),
            textLinkColor.map { it.color.toColor(context = context) }.toIntArray()
        )
    )
}
