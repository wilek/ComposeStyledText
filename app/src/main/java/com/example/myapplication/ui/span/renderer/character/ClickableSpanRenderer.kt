package com.example.myapplication.ui.span.renderer.character

import android.text.style.ClickableSpan
import android.view.View
import com.example.myapplication.ui.span.renderer.SpanRenderer
import com.example.myapplication.ui.string.combine.TextCombine.StyleSpan.CharacterStyle.Clickable

open class ClickableSpanRenderer : SpanRenderer<Clickable> {
    override fun renderSpan(styleSpan: Clickable): Any {
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
