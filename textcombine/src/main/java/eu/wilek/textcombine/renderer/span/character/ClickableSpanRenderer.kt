package eu.wilek.textcombine.renderer.span.character

import android.text.style.ClickableSpan
import android.view.View
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.Clickable
import eu.wilek.textcombine.renderer.span.SpanRenderer

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
