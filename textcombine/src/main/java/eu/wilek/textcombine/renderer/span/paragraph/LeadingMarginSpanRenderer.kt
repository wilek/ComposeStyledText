package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.text.style.LeadingMarginSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

internal class LeadingMarginSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as LeadingMargin
        return LeadingMarginSpan.Standard(
            styleSpan.first.toPx(context = context).roundToInt(),
            (styleSpan.rest ?: styleSpan.first).toPx(context = context).roundToInt()
        )
    }
}
