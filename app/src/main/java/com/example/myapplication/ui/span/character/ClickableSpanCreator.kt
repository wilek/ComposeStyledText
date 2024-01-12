package com.example.myapplication.ui.span.character

import android.content.Context
import android.text.style.ClickableSpan
import android.view.View
import com.example.myapplication.ui.span.TextCombineSpan
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Clickable

open class ClickableSpanCreator : TextCombineSpan<Clickable> {
    override fun createSpan(context: Context, styleSpan: Clickable): Any {
        return TextCombineClickableSpan(
            id = styleSpan.id,
            onClick = styleSpan.onClick
        )
    }

    private class TextCombineClickableSpan(
        private val id: String?,
        private val onClick: (String?) -> Unit
    ) : ClickableSpan() {

        override fun onClick(widget: View) {
            onClick(id)
        }
    }
}
