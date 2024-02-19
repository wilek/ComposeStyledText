package eu.wilek.textcombine.renderer.span

import eu.wilek.textcombine.TextCombine

interface SpanRenderer<T : TextCombine.StyleSpan> {

    fun renderSpan(styleSpan: T): Any
}
