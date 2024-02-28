package eu.wilek.textcombine.renderer.span.character

import android.content.Context
import android.text.style.AbsoluteSizeSpan
import eu.wilek.textcombine.TextCombine.StyleSpan
import eu.wilek.textcombine.TextCombine.StyleSpan.CharacterStyle.AbsoluteSize
import eu.wilek.textcombine.renderer.span.SpanRenderer
import eu.wilek.textcombine.util.toPx
import kotlin.math.roundToInt

internal class AbsoluteSizeSpanRenderer : SpanRenderer {

    override fun renderSpan(context: Context, styleSpan: StyleSpan): Any {
        styleSpan as AbsoluteSize
        return AbsoluteSizeSpan(styleSpan.size.toPx(context = context).roundToInt())
    }
}
