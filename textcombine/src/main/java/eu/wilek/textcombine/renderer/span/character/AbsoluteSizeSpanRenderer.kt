package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.AbsoluteSizeSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

open class AbsoluteSizeSpanRenderer(private val context: Context) : SpanRenderer<AbsoluteSize> {
    override fun renderSpan(styleSpan: AbsoluteSize): Any {
        return AbsoluteSizeSpan(styleSpan.size.toPx(context = context).roundToInt())
    }
}
