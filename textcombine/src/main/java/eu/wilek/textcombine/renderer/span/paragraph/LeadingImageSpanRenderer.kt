package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.text.style.DrawableMarginSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toImage

internal class LeadingImageSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as LeadingImage
        return DrawableMarginSpan(styleSpan.image.toImage(context = context, size = styleSpan.size, margin = styleSpan.margin))
    }
}
