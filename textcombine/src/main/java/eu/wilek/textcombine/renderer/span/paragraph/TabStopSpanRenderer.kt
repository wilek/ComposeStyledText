package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.text.style.TabStopSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

internal class TabStopSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as TabStop
        return TabStopSpan.Standard(styleSpan.offset.toPx(context = context).roundToInt())
    }
}
