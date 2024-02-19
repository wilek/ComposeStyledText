package eu.wilek.textcombine.renderer.span.paragraph

import android.content.Context
import android.text.style.DrawableMarginSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.ParagraphStyle.LeadingImage
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toImage

open class LeadingImageSpanRenderer(private val context: Context) : SpanRenderer<LeadingImage> {

    override fun renderSpan(styleSpan: LeadingImage): Any {
        return DrawableMarginSpan(styleSpan.image.toImage(context = context, size = styleSpan.size, margin = styleSpan.margin))
    }
}
