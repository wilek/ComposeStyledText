package eu.wilek.textcombine.renderer.spannable

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.renderer.TextCombineRendererImpl
import eu.wilek.textcombine.renderer.span.SpanRenderer
import kotlin.reflect.KClass

internal class SpannableCreator(private val spanRenderers: Map<KClass<out StyleSpan>, SpanRenderer>) {

    fun createSpan(
        context: Context,
        text: CharSequence,
        textSpans: List<TextCombineRendererImpl.PhraseSpan>
    ): CharSequence {
        val spannable = SpannableString(text)

        textSpans.forEach { textSpan ->
            textSpan.spans.forEach { span ->
                val spanRenderer = spanRenderers.getValue(span::class)
                spannable.setSpan(
                    spanRenderer.renderSpan(context = context, span),
                    textSpan.start,
                    textSpan.end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }

        return spannable
    }
}
