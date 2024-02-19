package eu.wilek.textcombine.renderer.span.paragraph

import android.text.style.LeadingMarginSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingMargin
import eu.wilek.textcombine.renderer.span.SpanRenderer

open class LeadingMarginSpanRenderer : SpanRenderer<LeadingMargin> {

    override fun renderSpan(styleSpan: LeadingMargin): Any {
        return LeadingMarginSpan.Standard(styleSpan.first, styleSpan.rest)
    }
}
