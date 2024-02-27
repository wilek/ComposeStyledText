package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.text.style.TabStopSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.TabStop
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

open class TabStopSpanRenderer(private val context: Context) : SpanRenderer<TabStop> {

    override fun renderSpan(styleSpan: TabStop): Any {
        return TabStopSpan.Standard(styleSpan.offset.toPx(context = context).roundToInt())
    }
}
