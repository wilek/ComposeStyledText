package eu.wilek.textcombine.renderer.span

import android.content.Context
import eu.wilek.textcombine.TextCombine.StyleSpan

interface SpanRenderer {

    fun renderSpan(context: Context, styleSpan: StyleSpan): Any
}
